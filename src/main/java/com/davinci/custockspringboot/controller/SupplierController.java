package com.davinci.custockspringboot.controller;

import com.davinci.custockspringboot.domain.dto.stock.CreateSupplierDto;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.service.stock.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/supplier")
    @Operation(summary = "Create new Supplier for Stock item(s)")
    public ResponseEntity<Supplier> createSupplier(@RequestBody CreateSupplierDto createSupplierDto) {
        return new ResponseEntity<>(supplierService.createSupplier(createSupplierDto), HttpStatus.OK);
    }
}
