package com.davinci.custockspringboot.controller;

import com.davinci.custockspringboot.domain.dto.stock.CreateBatchDto;
import com.davinci.custockspringboot.domain.model.stock.Batch;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.domain.repository.stock.BatchRepository;
import com.davinci.custockspringboot.domain.repository.stock.SupplierRepository;
import com.davinci.custockspringboot.service.stock.BatchService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/batches")
public class BatchController {
    private final BatchService batchService;
    private final SupplierRepository supplierRepository;
    private final BatchRepository batchRepository;

    @Autowired
    public BatchController(BatchService batchService, SupplierRepository supplierRepository, BatchRepository batchRepository) {
        this.batchService = batchService;
        this.supplierRepository = supplierRepository;
        this.batchRepository = batchRepository;
    }

    @GetMapping("/{batchId}")
    @Operation(summary = "Get information about a single batch")
    public ResponseEntity<?> fetchBatch(@PathVariable int batchId) {
        Optional<Batch> batch = batchRepository.findById(batchId);
        if (batch.isEmpty()) return new ResponseEntity<>("This batch does not exit.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(batch, HttpStatus.OK);
    }

    @GetMapping("/batch/{batchId}")
    @Operation(summary = "Get item(s) from a single batch")
    public ResponseEntity<?> fetchItemsFromBatch(@PathVariable int batchId) {
        if (batchRepository.findById(batchId).isEmpty()) {
            return new ResponseEntity<>("This batch does not exist!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(batchService.getAllItemsFromBatch(batchId), HttpStatus.OK);
    }


    @PostMapping("/batch")
    @Operation(summary = "Create new batch for Stock item(s)")
    public ResponseEntity<?> createBatch(@RequestBody CreateBatchDto createBatchDto) {
            Optional<Supplier> supplier = supplierRepository.findById(createBatchDto.getSupplier());
            if (supplier.isEmpty()) return new ResponseEntity<>("Supplier does not exist.", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(batchService.create(createBatchDto, supplier.get()), HttpStatus.OK);
    }









}
