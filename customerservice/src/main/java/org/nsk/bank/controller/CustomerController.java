package org.nsk.bank.controller;

import org.nsk.bank.pojo.Customer;
import org.nsk.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
private CustomerService customerService;


@PostMapping("/customers")
public Customer addNewCustomer(@RequestBody Customer customer){

    return customerService.addCustomer(customer);

}
@GetMapping("/customers/{id}")
public Customer getCustomerById(@PathVariable int id){

    return customerService.retCustomerById(id);

}


}
