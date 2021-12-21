package com.davinci.custockspringboot.service.stock;

import com.davinci.custockspringboot.domain.dto.stock.CreateCustomerDto;
import com.davinci.custockspringboot.domain.dto.stock.UpdateCustomerDto;
import com.davinci.custockspringboot.domain.model.stock.Customer;
import com.davinci.custockspringboot.domain.repository.stock.CustomerRepository;
import com.davinci.custockspringboot.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(CreateCustomerDto cc) {
        return customerRepository.save(
                new Customer(cc.getName(), cc.toString(),cc.getEmail(), cc.getAddress())
        );
    }

    public List<Customer> fetchAll() {
//        return customerRepository.getCustomersByUser(Utils.getContext().getName());
        return customerRepository.findAll();
    }

    public Customer update(Customer customer, UpdateCustomerDto cu) {
        customer.setAddress(cu.getAddress());
        customer.setName(cu.getName());
        customer.setEmail(cu.getEmail());
        customer.setPhoneNumber(cu.getPhoneNumber());
        return customerRepository.save(customer);
    }
}
