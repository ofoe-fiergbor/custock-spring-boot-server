package com.davinci.custockspringboot.configuration;

import com.davinci.custockspringboot.util.RoleLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineConfig {

    @Bean
    public CommandLineRunner createExchangeData(RoleLoader loader) {
        return args -> {
            loader.createRoles();
        };
    }
}
