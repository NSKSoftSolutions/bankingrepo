package org.nsk.bank.controllers;

import org.nsk.bank.domain.Transaction;
import org.nsk.bank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction-services/v1")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping("/trasfer")
    public ResponseEntity transfer(@RequestParam ("senderAccountNumber") long senderAccountNumber,@RequestParam("benificaryAccountNumber") long benificaryAccountNumber,@RequestParam("amount") double amount){

        // to ionvoke transferFunds method of TransactionService
      Transaction tx=transactionService.fundTransfer(senderAccountNumber,benificaryAccountNumber,amount);
      if(tx!=null){
          return ResponseEntity.ok("TX is SUCCESSFULLY COMPLETED");
      }else{
          return ResponseEntity.internalServerError().body("TX is NOT SUCCESSFULL ");
      }

    }





}
