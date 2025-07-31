package org.nsk.bank.repos;

import org.nsk.bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountServiceRepository extends JpaRepository<Account,Integer> {
    public Account findByAccountNumber(long accountNumber);
}
