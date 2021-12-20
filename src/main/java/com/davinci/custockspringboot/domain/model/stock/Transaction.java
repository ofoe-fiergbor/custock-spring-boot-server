package com.davinci.custockspringboot.domain.model.stock;

import com.davinci.custockspringboot.util.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "transactions")
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date timestamp;
    private String description;
    private double quantity;
    @ManyToOne
    private Item item;
    private TransactionType transactionType;
    @JsonIgnore
    private String user;

    public Transaction(String description, double quantity, Item item, TransactionType transactionType, String user) {
        this.timestamp = new Timestamp(new Date().getTime());
        this.description = description;
        this.quantity = quantity;
        this.item = item;
        this.transactionType = transactionType;
        this.user = user;
    }
}
