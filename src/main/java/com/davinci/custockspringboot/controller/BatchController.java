package com.davinci.custockspringboot.controller;

import com.davinci.custockspringboot.domain.dto.stock.CreateBatchDto;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.domain.repository.stock.SupplierRepository;
import com.davinci.custockspringboot.service.stock.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/batches")
public class BatchController {
    private final BatchService batchService;
    private final SupplierRepository supplierRepository;

    @Autowired
    public BatchController(BatchService batchService, SupplierRepository supplierRepository) {
        this.batchService = batchService;
        this.supplierRepository = supplierRepository;
    }

    @PostMapping("/batch")
    public ResponseEntity<?> createBatch(@RequestBody CreateBatchDto createBatchDto) {
            Optional<Supplier> supplier = supplierRepository.findById(createBatchDto.getSupplier());
            if (supplier.isEmpty()) return new ResponseEntity<>("Supplier does not exist.", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(batchService.create(createBatchDto, supplier.get()), HttpStatus.OK);
    }







}
