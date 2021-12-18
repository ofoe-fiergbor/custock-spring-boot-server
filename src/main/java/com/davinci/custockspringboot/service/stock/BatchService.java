package com.davinci.custockspringboot.service.stock;

import com.davinci.custockspringboot.domain.dto.stock.CreateBatchDto;
import com.davinci.custockspringboot.domain.model.stock.Batch;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.domain.repository.stock.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BatchService {

    private final BatchRepository batchRepository;


    @Autowired
    public BatchService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;

    }

    public Batch create(CreateBatchDto createBatchDto, Supplier supplier) {
        return batchRepository.save(new Batch(createBatchDto.getName(), supplier));
    }

}
