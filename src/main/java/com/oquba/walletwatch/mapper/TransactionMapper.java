package com.oquba.walletwatch.mapper;

import com.oquba.walletwatch.dto.TransactionDto;
import com.oquba.walletwatch.models.Transaction;
import com.oquba.walletwatch.models.TransactionGraphData;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount())
                .transactionDate(transactionDto.getTransactionDate())
                .type(transactionDto.getType())
                .createdBy(transactionDto.getCreatedBy())
                .category(transactionDto.getCategory())
                .build();
        return transaction;
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .transactionDate(transaction.getTransactionDate())
                .type(transaction.getType())
                .createdBy(transaction.getCreatedBy())
                .category(transaction.getCategory())
                .build();
        return transactionDto;
    }

    public static TransactionGraphData mapToTransactionGraphData(TransactionDto transactionDto) {
        TransactionGraphData transactionGraphData = TransactionGraphData.builder()
                .transactionDate(transactionDto.getTransactionDate())
                .amount(transactionDto.getAmount())
                .type(transactionDto.getType())
                .build();
        return transactionGraphData;
    }
}
