package com.davinci.custockspringboot.domain.dto.stock;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateSupplierDto {
    private String name;
    private String phoneNumber;
    private String address;
}
