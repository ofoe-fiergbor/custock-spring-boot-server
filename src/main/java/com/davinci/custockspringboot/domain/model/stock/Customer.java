package com.davinci.custockspringboot.domain.model.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private Date timestamp;

    public Customer(String name, String phoneNumber, String email, String address) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.address = address;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
