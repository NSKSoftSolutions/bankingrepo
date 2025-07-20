package org.nsk.bank.services;

import org.nsk.bank.repos.CustomerRepository;
import org.nsk.bank.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
   private CustomerRepository customerRepository;


    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer retCustomerById(int id) {
      return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer not found with id: " + id));
    }
}
