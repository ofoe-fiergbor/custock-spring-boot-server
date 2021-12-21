package com.davinci.custockspringboot.domain.model.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Data
@Entity
@Table
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double quantity;
    private String unitMeasurement;
    @ManyToOne
    private Supplier supplier;
    private Date timestamp;
    @JsonIgnore
    private String user;

    public Item(String name, String description, double quantity, String unitMeasurement, Supplier supplier, String user) {
        this.name = name;
        this.description = description;
        this.unitMeasurement = unitMeasurement;
        this.supplier = supplier;
        this.timestamp = new Timestamp(new Date().getTime());
        this.user = user;
        this.quantity = quantity;
    }

    public void increaseQuantity(double q) {
        this.quantity += q;
    }

    public void decreaseQuantity(double q) {
        this.quantity -= q;
    }
}
