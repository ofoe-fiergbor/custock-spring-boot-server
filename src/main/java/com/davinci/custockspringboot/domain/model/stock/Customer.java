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
    private String socialMedia;
    private String socialMediaHandle;
    private Date timestamp;

    public Customer(String name, String phoneNumber, String socialMedia, String socialMediaHandle) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.socialMedia = socialMedia;
        this.socialMediaHandle = socialMediaHandle;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}
