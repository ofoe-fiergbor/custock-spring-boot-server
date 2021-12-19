package com.davinci.custockspringboot.domain.repository.stock;

import com.davinci.custockspringboot.domain.model.stock.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
}
