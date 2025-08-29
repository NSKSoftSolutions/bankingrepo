package org.nsk.bank.controllers;

import org.nsk.bank.domain.Benificary;
import org.nsk.bank.services.BenificaryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("benificary/api/v1")
public class BenificaryController {

    @Autowired
    private BenificaryServices benificaryServices;


    @PostMapping("/benificaries")
    public ResponseEntity<Benificary> createBenificary(@RequestBody Benificary benificary){

        Benificary savedBenificary=benificaryServices.addBenificary(benificary);
        if(savedBenificary!=null){
           return ResponseEntity.ok(savedBenificary);
        }else{
            return ResponseEntity.internalServerError().body(savedBenificary);
        }

    }

}
