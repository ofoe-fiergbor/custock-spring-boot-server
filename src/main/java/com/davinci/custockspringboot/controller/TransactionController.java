package com.davinci.custockspringboot.controller;

import com.davinci.custockspringboot.domain.dto.stock.CreateTransactionDto;
import com.davinci.custockspringboot.domain.model.stock.Item;
import com.davinci.custockspringboot.domain.model.stock.Transaction;
import com.davinci.custockspringboot.domain.repository.stock.ItemRepository;
import com.davinci.custockspringboot.domain.repository.stock.TransactionRepository;
import com.davinci.custockspringboot.service.stock.TransactionService;
import com.davinci.custockspringboot.util.Utils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final ItemRepository itemRepository;
    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    public TransactionController(ItemRepository itemRepository, TransactionService transactionService, TransactionRepository transactionRepository) {
        this.itemRepository = itemRepository;
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    @Operation(description = "Fetch all available transactions.")
    public ResponseEntity<List<Transaction>> fetchTransactions() {
        return new ResponseEntity<>(transactionService.fetchAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(description = "Fetch specific available transaction.")
    public ResponseEntity<?> fetchTransaction(@PathVariable int id) {
        Optional<Transaction>  transaction = transactionRepository.getTransactionByUserAndId(
                Utils.getContext().getName(),
                id
        );
        if (transaction.isEmpty()) return new ResponseEntity<>("No transaction with id: "+id, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
    }

    @PostMapping("/in")
    @Operation(description = "Create an inward transaction.")
    public ResponseEntity<?> inwardTransaction(@RequestBody CreateTransactionDto ct) {
        Optional<Item> item = itemRepository.findById(ct.getItemId());
        if (item.isEmpty()) {
            return new ResponseEntity<>("Item with id: "+ct.getItemId()+" does not exist.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(transactionService.createInwardTransaction(ct, item.get()), HttpStatus.OK);
    }

    @PostMapping("/out")
    @Operation(description = "Create an outward transaction.")
    public ResponseEntity<?> outwardTransaction(@RequestBody CreateTransactionDto ct) {
        Optional<Item> item = itemRepository.findById(ct.getItemId());
        if (item.isEmpty()) {
            return new ResponseEntity<>("Item with id: "+ct.getItemId()+" does not exist.", HttpStatus.BAD_REQUEST);
        }
        if (item.get().getQuantity() - ct.getQuantity() < 0) {
            return new ResponseEntity<>("You don't have enough stock to perform this transaction.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(transactionService.createOutwardTransaction(ct, item.get()), HttpStatus.OK);
    }
}
