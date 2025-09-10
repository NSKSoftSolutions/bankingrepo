package org.nsk.bank.services;

import org.nsk.bank.domain.Benificary;
import org.nsk.bank.repos.BenificaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BenificaryServices {


  @Autowired
  private BenificaryRepository benificaryRepository;

  public Benificary addBenificary(Benificary benificary){
     return benificaryRepository.save(benificary);
  }

}
