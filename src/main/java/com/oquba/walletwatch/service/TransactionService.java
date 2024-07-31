package com.oquba.walletwatch.service;

import com.oquba.walletwatch.dto.TransactionDto;
import com.oquba.walletwatch.models.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<TransactionDto> getAllTransactions(Long id);
    void createTransaction(Long categoryId, TransactionDto transactionDto);
    Transaction saveTransaction(TransactionDto transaction);

    TransactionDto findTransactionByID(long transactionId);

    void updateTransaction(TransactionDto transaction);

    void delete(Long transactionId);
    List<TransactionDto> searchCategory(String query);
    List<TransactionDto> searchCategoryByUserId(String query, Long userId);
}
