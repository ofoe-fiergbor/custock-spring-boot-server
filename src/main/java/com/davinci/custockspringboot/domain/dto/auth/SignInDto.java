package com.davinci.custockspringboot.domain.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInDto {
    private String usernameOrEmail;
    private String password;
}
