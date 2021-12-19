package com.davinci.custockspringboot.domain.repository.stock;

import com.davinci.custockspringboot.domain.model.stock.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
