package com.leovegas.walletmanager.repository;

import com.leovegas.walletmanager.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Simin
 * @created 09/09/2022 - 3:51 PM
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
