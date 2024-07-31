package com.oquba.walletwatch.repository;

import com.oquba.walletwatch.dto.TransactionDto;
import com.oquba.walletwatch.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT c FROM Transaction c WHERE c.createdBy.id = :userId " +
            "ORDER BY c.transactionDate")
    List<Transaction> findByCreatedBy(Long userId);
    @Query("SELECT t from Transaction t WHERE t.category.name LIKE CONCAT('%', :query, '%') " +
            "ORDER BY t.transactionDate")
    List<Transaction> searchCategory(String query);
    @Query("SELECT t FROM Transaction t WHERE t.category.name LIKE CONCAT('%', :query, '%') AND t.createdBy.id = :userId " +
            "ORDER BY t.transactionDate")
    List<Transaction> searchCategoryByUserId(String query, Long userId);
}
