package org.nsk.bank.services;

import org.nsk.bank.constants.NSKBANKConstants;
import org.nsk.bank.domain.Transaction;
import org.nsk.bank.repos.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction fundTransfer(long senderAccountNumber, long benificaryAccountNumber, double amount){
        //
        //
        // http://localhost:8082/accountservices/v1/accounts/3912706742/debit?amount=500
        try {
            String debitURL = NSKBANKConstants.ACC_SRV_BASE_URL + senderAccountNumber + NSKBANKConstants.DEBIT_URI + amount;
            String creditURL = NSKBANKConstants.ACC_SRV_BASE_URL + benificaryAccountNumber + NSKBANKConstants.CREDIT_URI + amount;

            restTemplate.put(debitURL, null);
            restTemplate.put(creditURL, null);

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setSenderAccountNumber(senderAccountNumber);
            transaction.setBenificaryAccountNumber(benificaryAccountNumber);
            transaction.setStatus("SUCCESS");
            transactionRepository.save(transaction);
            return transaction;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }



}
