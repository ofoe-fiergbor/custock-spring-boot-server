package com.davinci.custockspringboot.controller;


import com.davinci.custockspringboot.domain.dto.stock.CreateItemDto;
import com.davinci.custockspringboot.domain.dto.stock.EditItemDto;
import com.davinci.custockspringboot.domain.model.stock.Item;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.domain.repository.stock.ItemRepository;
import com.davinci.custockspringboot.domain.repository.stock.SupplierRepository;
import com.davinci.custockspringboot.service.stock.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final SupplierRepository supplierRepository;

    public ItemController(ItemService itemService, ItemRepository itemRepository, SupplierRepository supplierRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
        this.supplierRepository = supplierRepository;
    }
    @GetMapping
    @Operation(description = "Fetch all available stock items.")
    public ResponseEntity<List<Item>> fetchAllItems() {
        return new ResponseEntity<>(itemService.fetchAllItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Fetch specific available stock item by item id.")
    public ResponseEntity<?> fetchSingleItem(@PathVariable int id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) {
            return new ResponseEntity<>("No item exist for id: "+id, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(item.get(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "Create new stock item.")
    public ResponseEntity<?> createNewItem(@RequestBody CreateItemDto createItemDto) {
        Optional<Supplier> supplier = supplierRepository.findById(createItemDto.getSupplierId());
        if (supplier.isEmpty()) {
            return new ResponseEntity<>("There is no supplier with id: "+createItemDto.getSupplierId(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemService.createNewItem(createItemDto, supplier.get()), HttpStatus.OK);
    }


    @PutMapping
    @Operation(description = "Update exiting stock item")
    public ResponseEntity<?> updateItem(@RequestBody EditItemDto editItemDto) {
        Optional<Item> item = itemRepository.findById(editItemDto.getId());
        if (item.isEmpty()) {
            return new ResponseEntity<>("There is not item with id: " +editItemDto.getId(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemService.editItem(editItemDto, item.get()), HttpStatus.OK);
    }

}
