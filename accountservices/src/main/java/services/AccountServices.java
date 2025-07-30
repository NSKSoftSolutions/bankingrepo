package services;

import domain.Account;
import dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class AccountServices {


    @Autowired
    private RestTemplate restTemplate;

    public void addAccount(Account account) {

        log.info("Inside AddAccount Method of AccountServices");
        log.info("adding account with details: {}", account);

        log.info("Checking existance of Customer with ID: {}", account.getCustomerId());
        if (validateCustomer(account.getCustomerId())) {
            log.info("Customer With ID: {} exists, proceeding to add account", account.getCustomerId());

        } else {
            log.error("Customer with ID: {} does not exist", account.getCustomerId());
            throw new RuntimeException("Customer with ID: " + account.getCustomerId() + " does not exist");
        }

    }

    private boolean validateCustomer(int customerId) {
        boolean isValidCustomer = false;
        log.info("Validating the Customer with customerId:{},", customerId);
        if (customerId == 0 || customerId == -1) {
            log.info("{Please Send the Valid Customer Details:{}", customerId);
        } else {
            String BASE_URL = "http://localhost:8081";
            String SERVICE_NAME = "api/v1/customers";
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

}
