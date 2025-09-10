package org.nsk.bank.repos;

import org.nsk.bank.domain.Benificary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenificaryRepository extends JpaRepository<Benificary,Long> {
    public Benificary findByBenificaryAccountNumber(long accountNumber);
    public Benificary findByCustomerAccountNumber(long customerAccountNumber);
}
