package com.davinci.custockspringboot.domain.dto.stock;

import lombok.Data;

@Data
public class UpdateCustomerDto {
    private int id;
    private String name;
    private String phoneNumber;
    private String socialMedia;
    private String socialMediaHandle;
}
