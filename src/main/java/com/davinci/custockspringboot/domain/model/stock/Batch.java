package com.davinci.custockspringboot.domain.model.stock;

import com.davinci.custockspringboot.domain.model.stock.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "batches")
@NoArgsConstructor
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private Date timestamp;

    @ManyToOne(targetEntity = Supplier.class)
    private Supplier supplier;

    public Batch(String name, Supplier supplier) {
        this.name = name;
        this.supplier = supplier;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
