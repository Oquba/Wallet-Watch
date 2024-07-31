package com.oquba.walletwatch.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    private float amount;
    private String type; // Either "Spending" or "Deposit"
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Date getTransactionDate() {
        return this.transactionDate;
    }

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;
}