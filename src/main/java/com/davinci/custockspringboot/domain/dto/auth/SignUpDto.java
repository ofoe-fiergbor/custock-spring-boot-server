package com.davinci.custockspringboot.domain.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

}
