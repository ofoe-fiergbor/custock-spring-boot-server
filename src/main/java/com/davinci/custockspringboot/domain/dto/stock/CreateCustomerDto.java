package com.davinci.custockspringboot.domain.dto.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCustomerDto {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
}
