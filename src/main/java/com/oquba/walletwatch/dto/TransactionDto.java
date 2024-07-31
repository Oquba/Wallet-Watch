package com.oquba.walletwatch.dto;

import com.oquba.walletwatch.models.Category;
import com.oquba.walletwatch.models.UserEntity;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class TransactionDto {
    private Long id;
    @NotNull(message = "Transaction date should not be empty")
    @Past(message = "Transaction date should be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transactionDate;
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be non-negative")
    @DecimalMax(value = "10000000.0", inclusive = true, message = "Amount too big.")
    private float amount;
    private String type;
    private UserEntity createdBy;
    private Category category;

}
