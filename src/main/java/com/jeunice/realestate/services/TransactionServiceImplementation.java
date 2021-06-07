package com.jeunice.realestate.services;

import com.jeunice.realestate.dao.TransactionRepository;
import com.jeunice.realestate.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//Implements the interface TransactionService
public class TransactionServiceImplementation {
    @Autowired
    private TransactionRepository transactionRepository;

    //    The method will basically return a list of all transactions to the controller
    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }
    //Save Method for Transaction
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    //Get Transaction by Id
    public Optional<Transaction>getTransactionsByTransactionCode(String transactionCode) {
        return transactionRepository.findById(transactionCode);
    }

    //Delete method using transactionCode
    public void deleteTransaction(String transactionCode) {
        transactionRepository.findById(transactionCode);
    }
}
