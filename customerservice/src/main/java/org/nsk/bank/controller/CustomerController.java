package org.nsk.bank.controller;

import org.nsk.bank.pojo.Customer;
import org.nsk.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
private CustomerService customerService;


@PostMapping("/customers")
public Customer addNewCustomer(@RequestBody Customer customer){

    return customerService.addCustomer(customer);

}




}
