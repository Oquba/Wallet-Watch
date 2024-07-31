package com.oquba.walletwatch.models;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class TransactionGraphData {
    private Date transactionDate;
    private float amount;
    private String type;

    public TransactionGraphData(Date transactionDate, float amount, String type) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.type = type;
    }
}
