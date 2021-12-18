package com.davinci.custockspringboot.service.stock;

import com.davinci.custockspringboot.domain.dto.stock.CreateItemDto;
import com.davinci.custockspringboot.domain.model.stock.Batch;
import com.davinci.custockspringboot.domain.model.stock.Item;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.domain.repository.stock.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService {

    private final ItemRepository itemRepository;


    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item create(CreateItemDto createItemDto, Supplier supplier, Batch batch) {
        Item newItem = new Item(
                createItemDto.getName(),
                createItemDto.getQuantity(),
                createItemDto.getUnitOfMeasure(),
                createItemDto.getDescription(), supplier, batch);
        return itemRepository.save(newItem);
    }
}
