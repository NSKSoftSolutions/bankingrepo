package org.nsk.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class TransactionserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionserviceApplication.class, args);
    }

}
