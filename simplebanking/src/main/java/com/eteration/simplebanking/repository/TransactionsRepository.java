package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {

    @Query("select k from TransactionsEntity k where k.accountId = :accountId")
    List<TransactionsEntity> findByAccountId(@Param("accountId") Long id);
}
