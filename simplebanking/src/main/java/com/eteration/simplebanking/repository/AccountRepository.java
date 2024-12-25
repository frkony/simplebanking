package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<BankAccountEntity, Long> {

    @Query("select k from BankAccountEntity k where k.accountNumber = :account")
    BankAccountEntity findByAccountNumber(@Param("account") String account);
}
