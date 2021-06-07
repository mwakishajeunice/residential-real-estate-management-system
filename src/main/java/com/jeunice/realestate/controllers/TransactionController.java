package com.jeunice.realestate.controllers;
import com.jeunice.realestate.models.Transaction;
import com.jeunice.realestate.services.TransactionServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
//@RequestMapping("/transactions")
//@RestController
public class TransactionController {
    @Autowired
    private TransactionServiceImplementation transactionServiceImplementation;

    @RequestMapping("/transactions")
    public String viewHomePage(Model model){
        model.addAttribute("listTransactions", transactionServiceImplementation.getAllTransactions());
        return "transactions_index";
    }
    //Method Handler for addNewTransaction request
    @GetMapping("/addNewTransaction")
    public String addNewTransaction(Model model) {
        //Create model attribute to bind data accessed from thymeleaf template(this empty transaction object)
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "new_transaction";
    }

    //Updating method
    //@PostMapping("/saveNewTransaction")
    @RequestMapping("/saveNewTransaction")
    //Bind the model attributes data to the transaction
    public String saveTransaction(@ModelAttribute("transaction") Transaction transaction) {
        // save transaction to database
        transactionServiceImplementation.saveTransaction(transaction);
        //redirect to home page
        return "redirect:/transactions";

    }

    //Method Handler for addNewTransaction request
    //@PutMapping("/edit/{transactionCode}")
    @RequestMapping("/editTransactions/{transactionCode}")
    public ModelAndView updateTransaction(@PathVariable(name = "transactionCode") String transactionCode){

        ModelAndView modelAndView = new ModelAndView("edit_transaction");

        Optional<Transaction> transaction = transactionServiceImplementation.getTransactionsByTransactionCode(transactionCode);
        modelAndView.addObject("transaction",transaction);

        return modelAndView;
    }

    //@DeleteMapping("/delete/{transactionCode}")
    @RequestMapping("/deleteTransactions/{transactionCode}")
    public String deleteTransaction(@PathVariable(name = "transactionCode") String transactionCode){
        transactionServiceImplementation.deleteTransaction(transactionCode);
        return "redirect:/transactions";
    }
}
