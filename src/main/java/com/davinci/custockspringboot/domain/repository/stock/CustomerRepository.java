package com.davinci.custockspringboot.domain.repository.stock;

import com.davinci.custockspringboot.domain.model.stock.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    List<Customer> getCustomersByUser(String user);
//    Optional<Customer> getCustomerByIdAndUser(int id, String user);

}
