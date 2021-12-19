package com.davinci.custockspringboot.controller;

import com.davinci.custockspringboot.domain.dto.stock.CreateItemDto;
import com.davinci.custockspringboot.domain.model.stock.Batch;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.domain.repository.stock.BatchRepository;
import com.davinci.custockspringboot.domain.repository.stock.SupplierRepository;
import com.davinci.custockspringboot.service.stock.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    private final ItemService itemService;
    private final SupplierRepository supplierRepository;
    private final BatchRepository batchRepository;

    public ItemController(ItemService itemService, SupplierRepository supplierRepository, BatchRepository batchRepository) {
        this.itemService = itemService;
        this.supplierRepository = supplierRepository;
        this.batchRepository = batchRepository;
    }

    @PostMapping("/item")
    @Operation(summary = "Create new Stock item(s)")
    public ResponseEntity<?> createItems(@RequestBody CreateItemDto createItemDto) {
        Optional<Supplier> supplier = supplierRepository.findById(createItemDto.getSupplier());
        if (supplier.isEmpty()) {
            return new ResponseEntity<>("This supplier does not exist!", HttpStatus.BAD_REQUEST);
        }
        Optional<Batch> batch = batchRepository.findById(createItemDto.getBatch());
        if (batch.isPresent()) {
            return new ResponseEntity<>("This batch does not exist!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemService.create(createItemDto, supplier.get(), batch.get()), HttpStatus.OK);
    }
}
