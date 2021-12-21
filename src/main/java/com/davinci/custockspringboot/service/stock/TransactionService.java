package com.davinci.custockspringboot.service.stock;

import com.davinci.custockspringboot.domain.dto.stock.CreateTransactionDto;
import com.davinci.custockspringboot.domain.model.stock.Item;
import com.davinci.custockspringboot.domain.model.stock.Transaction;
import com.davinci.custockspringboot.domain.repository.stock.ItemRepository;
import com.davinci.custockspringboot.domain.repository.stock.TransactionRepository;
import com.davinci.custockspringboot.util.Utils;
import com.davinci.custockspringboot.util.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, ItemRepository itemRepository) {
        this.transactionRepository = transactionRepository;
        this.itemRepository = itemRepository;
    }

    public Transaction createInwardTransaction(CreateTransactionDto ct, Item item) {
             item.increaseQuantity(ct.getQuantity());
            itemRepository.save(item);
            Transaction newTransaction = new Transaction(
                    ct.getDescription(),
                    ct.getQuantity(),
                    item,
                    TransactionType.INWARD,
                    Utils.getContext().getName());
        return transactionRepository.save(newTransaction);
    }

    public Transaction createOutwardTransaction(CreateTransactionDto ct, Item item) {
            item.decreaseQuantity(ct.getQuantity());
            itemRepository.save(item);
            Transaction newTransaction = new Transaction(
                    ct.getDescription(),
                    ct.getQuantity(),
                    item,
                    TransactionType.OUTWARD,
                    Utils.getContext().getName());
        return transactionRepository.save(newTransaction);
    }

    public List<Transaction> fetchAllTransactions() {
        return transactionRepository.getTransactionsByUser(Utils.getContext().getName());
    }


}
