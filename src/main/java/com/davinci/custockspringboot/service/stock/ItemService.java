package com.davinci.custockspringboot.service.stock;


import com.davinci.custockspringboot.domain.dto.stock.CreateItemDto;
import com.davinci.custockspringboot.domain.dto.stock.EditItemDto;
import com.davinci.custockspringboot.domain.model.stock.Item;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.domain.repository.stock.ItemRepository;
import com.davinci.custockspringboot.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemService {


    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createNewItem(CreateItemDto createItemDto, Supplier supplier) {
        Item newItem = new Item(createItemDto.getName(),
                createItemDto.getDescription(),
                0.00,
                createItemDto.getUnitMeasurement(),
                supplier, Utils.getContext().getName());
        return itemRepository.save(newItem);
    }

    public List<Item> fetchAllItems() {
        return itemRepository.getItemsByUser(Utils.getContext().getName());
    }


    public Item editItem(EditItemDto editItemDto, Item item) {
        item.setName(editItemDto.getName());
        item.setDescription(editItemDto.getDescription());
        item.setUnitMeasurement(editItemDto.getUnitMeasurement());
        return itemRepository.save(item);
    }


}
