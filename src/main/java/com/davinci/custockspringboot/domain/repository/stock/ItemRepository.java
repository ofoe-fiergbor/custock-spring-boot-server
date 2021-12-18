package com.davinci.custockspringboot.domain.repository.stock;

import com.davinci.custockspringboot.domain.model.stock.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
