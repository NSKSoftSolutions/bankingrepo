package org.nsk.bank.services;

import org.nsk.bank.domain.Account;
import org.nsk.bank.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.nsk.bank.repos.AccountServiceRepository;

import java.util.Random;

@Service
@Slf4j
public class AccountServices {

    @Autowired
    private AccountServiceRepository accountServiceRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Account addAccount(Account account) {

        log.info("Inside AddAccount Method of AccountServices");
        log.info("adding account with details: {}", account);

        log.info("Checking existance of Customer with ID: {}", account.getCustomerId());
        if (validateCustomer(account.getCustomerId())) {
            log.info("Customer With ID: {} exists, proceeding to add account", account.getCustomerId());
            log.info("Validated the Customer:{} with CustomerId, Proceeding for account creation",account.getCustomerId());
            account.setAccountNumber(getAccountNumber());
            Account createdAccount=accountServiceRepository.save(account);
            return createdAccount;
        } else {
            log.error("Customer with ID: {} does not exist", account.getCustomerId());
            throw new RuntimeException("Customer with ID: " + account.getCustomerId() + " does not exist");
        }

    }

    private static long getAccountNumber() {
        Random random = new Random();
        // Generate number between 1000000000 (inclusive) and 9999999999 (inclusive)
        long min = 1_000_000_000L;
        long max = 9_999_999_999L;
        long number = min + ((long)(random.nextDouble() * (max - min + 1)));
        return number;
    }

    public Account fetchAccount(long accountNumber){
        log.info("Inide FetchAccount Method of AccountServices");
        log.info("Fetching account with accountNumber: {}", accountNumber);
        Account account=accountServiceRepository.findByAccountNumber(accountNumber);
        return account;
    }

    private boolean validateCustomer(int customerId) {
        boolean isValidCustomer = false;
        log.info("Validating the Customer with customerId:{},", customerId);
        if (customerId == 0 || customerId == -1) {
            log.info("{Please Send the Valid Customer Details:{}", customerId);
        } else {
            String BASE_URL = "http://localhost:8081";
            String SERVICE_NAME = "/api/v1/customers";
            String URL = BASE_URL + SERVICE_NAME + "/" + customerId;
            ResponseEntity<Customer> customerResponseEntity = restTemplate.getForEntity(URL, Customer.class);
            if (customerResponseEntity.getStatusCode().is2xxSuccessful()) {
                Customer customer = (Customer) customerResponseEntity.getBody();
                log.info("fetched the customer :{} for given customer Id:{}", customer, customerId);
                isValidCustomer = true;
            }
        }
        return isValidCustomer;
    }

    public Account debitAccount(long accountNumber, double amount) {
        log.info("Inside debitAccount Method of AccountServices");
        Account account = accountServiceRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            log.error("Account with number {} not found", accountNumber);
            throw new RuntimeException("Account not found");
        }
        if (amount <= 0) {
            log.error("Invalid debit amount: {}", amount);
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (account.getBalance() < amount) {
            log.error("Insufficient balance in account {}: requested {}, available {}", accountNumber, amount, account.getBalance());
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        Account updatedAccount = accountServiceRepository.save(account);
        log.info("Debited {} from account {}. New balance: {}", amount, accountNumber, updatedAccount.getBalance());
        return updatedAccount;
    }

    public Account creditAccount(long accountNumber, double amount) {
        log.info("Inside creditAccount Method of AccountServices");
        Account account = accountServiceRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            log.error("Account with number {} not found", accountNumber);
            throw new RuntimeException("Account not found");
        }
        if (amount <= 0) {
            log.error("Invalid credit amount: {}", amount);
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        account.setBalance(account.getBalance() + amount);
        Account updatedAccount = accountServiceRepository.save(account);
        log.info("Credited {} to account {}. New balance: {}", amount, accountNumber, updatedAccount.getBalance());
        return updatedAccount;
    }

}
