package org.nsk.bank.controllers;

import org.nsk.bank.domain.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.nsk.bank.services.AccountServices;

@RestController
@Slf4j
@RequestMapping("/accountservices/v1")
public class AccounServiceController {


    @Autowired
    private AccountServices accountServices;


    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {

        log.info("Creating account for a given Customer :{}", account.getCustomerId());
        Account accnt = accountServices.addAccount(account);
        if (accnt == null) {
            throw new RuntimeException("Problem in Creating the Account for given Customer:{}");
        } else {
            log.info("Account Created Successfully");
            return ResponseEntity.ok(accnt);
        }
    }

    @GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<Account> retAccountBy(@PathVariable long accountNumber) {
        log.info("Retrieving account by account number :{}", accountNumber);
        Account account = accountServices.fetchAccount(accountNumber);
        if (account != null) {
            log.info("Account Retrieved Successfully");
            return ResponseEntity.ok(account);
        } else {
            throw new RuntimeException("Account Not Found");
        }
    }
}
