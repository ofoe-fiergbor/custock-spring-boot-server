package com.davinci.custockspringboot.controller;

import com.davinci.custockspringboot.domain.dto.stock.CreateCustomerDto;
import com.davinci.custockspringboot.domain.dto.stock.UpdateCustomerDto;
import com.davinci.custockspringboot.domain.model.stock.Customer;
import com.davinci.custockspringboot.domain.repository.stock.CustomerRepository;
import com.davinci.custockspringboot.service.stock.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/")
    @Operation(summary = "Create new Customer for Stock item(s)")
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return new ResponseEntity<>(customerService.create(createCustomerDto), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Fetch all customers")
    public ResponseEntity<List<Customer>> fetchAllCustomers(){
        return new ResponseEntity<>(customerService.fetchAll(), HttpStatus.OK);
    }

    @PutMapping("/")
    @Operation(summary = "Create new Customer for Stock item(s)")
    public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerDto updateCustomerDto) {
        Optional<Customer> customer = customerRepository.findById(updateCustomerDto.getId());
        if (customer.isEmpty()) return new ResponseEntity<>("Custer does not exit.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(customerService.update(customer.get(), updateCustomerDto), HttpStatus.OK);
    }
}
