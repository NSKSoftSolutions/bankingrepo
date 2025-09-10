package org.nsk.bank.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class NSKBANKConfiguration {


    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplte=new RestTemplate();
        return restTemplte;
    }


}
