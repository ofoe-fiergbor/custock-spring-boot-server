package com.davinci.custockspringboot.service.stock;

import com.davinci.custockspringboot.domain.dto.stock.CreateSupplierDto;
import com.davinci.custockspringboot.domain.model.stock.Supplier;
import com.davinci.custockspringboot.domain.repository.stock.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;


    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(CreateSupplierDto cs) {
        return supplierRepository.save(new Supplier(cs.getName(), cs.getPhoneNumber(), cs.getEmail(), cs.getAddress()));
    }


}
