package com.davinci.custockspringboot.service.stock;

import com.davinci.custockspringboot.domain.dto.stock.CreateBatchDto;
import com.davinci.custockspringboot.domain.model.stock.Batch;
import com.davinci.custockspringboot.domain.model.stock.Item;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.domain.repository.stock.BatchRepository;
import com.davinci.custockspringboot.domain.repository.stock.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BatchService {

    private final BatchRepository batchRepository;
    private final ItemRepository itemRepository;


    @Autowired
    public BatchService(BatchRepository batchRepository, ItemRepository itemRepository) {
        this.batchRepository = batchRepository;
        this.itemRepository = itemRepository;
    }

    public Batch create(CreateBatchDto createBatchDto, Supplier supplier) {
        return batchRepository.save(new Batch(createBatchDto.getName(), supplier));
    }

    public List<Item> getAllItemsFromBatch(int batchId) {
       return itemRepository.fetchItemsByBatch(batchId);
    }

}
