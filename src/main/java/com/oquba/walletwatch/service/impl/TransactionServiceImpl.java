package com.oquba.walletwatch.service.impl;

import com.oquba.walletwatch.dto.TransactionDto;
import com.oquba.walletwatch.models.Category;
import com.oquba.walletwatch.models.Transaction;
import com.oquba.walletwatch.models.UserEntity;
import com.oquba.walletwatch.repository.CategoryRepository;
import com.oquba.walletwatch.repository.TransactionRepository;
import com.oquba.walletwatch.repository.UserRepository;
import com.oquba.walletwatch.security.SecurityUtil;
import com.oquba.walletwatch.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.oquba.walletwatch.mapper.TransactionMapper.mapToTransaction;
import static com.oquba.walletwatch.mapper.TransactionMapper.mapToTransactionDto;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CategoryRepository categoryRepository,
                                  UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<TransactionDto> getAllTransactions(Long id) {
        List<Transaction> transactions = transactionRepository.findByCreatedBy(id);
        return transactions.stream().map((transaction) -> mapToTransactionDto(transaction)).collect(Collectors.toList());
    }

    @Override
    public void createTransaction(Long categoryId, TransactionDto transactionDto) {
        Category category = categoryRepository.findById(categoryId).get();
        Transaction transaction = mapToTransaction(transactionDto);
        transaction.setCategory(category);
        transactionRepository.save(transaction);
    }

    @Override
    public Transaction saveTransaction(TransactionDto transactionDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Transaction transaction = mapToTransaction(transactionDto);
        transaction.setCreatedBy(user);
        return transactionRepository.save(transaction);
    }

    @Override
    public TransactionDto findTransactionByID(long transactionID) {
        Transaction transaction = transactionRepository.findById(transactionID).get();
        return mapToTransactionDto(transaction);
    }

    @Override
    public void updateTransaction(TransactionDto transactionDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Transaction transaction = mapToTransaction(transactionDto);
        transaction.setCreatedBy(user);
        transactionRepository.save(transaction);
    }

    @Override
    public void delete(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public List<TransactionDto> searchCategory(String query) {
        List<Transaction> transactions = transactionRepository.searchCategory(query);
        return transactions.stream().map(transaction -> mapToTransactionDto(transaction)).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> searchCategoryByUserId(String query, Long userId) {
        List<Transaction> transactions = transactionRepository.searchCategoryByUserId(query, userId);
        return transactions.stream().map(transaction -> mapToTransactionDto(transaction)).collect(Collectors.toList());
    }
}
