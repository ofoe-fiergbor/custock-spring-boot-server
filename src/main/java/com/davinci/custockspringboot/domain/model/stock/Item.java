package com.davinci.custockspringboot.domain.model.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "items")
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double quantity;
    private Date timestamp;
    private String unitOfMeasure;
    private String description;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Batch batch;

    public Item(String name, double quantity, String unitOfMeasure, String description, Supplier supplier, Batch batch) {
        this.name = name;
        this.batch = batch;
        this.supplier = supplier;
        this.quantity = quantity;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
