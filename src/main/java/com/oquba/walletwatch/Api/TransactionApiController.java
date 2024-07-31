package com.oquba.walletwatch.Api;

import com.oquba.walletwatch.dto.TransactionDto;
import com.oquba.walletwatch.models.TransactionGraphData;
import com.oquba.walletwatch.mapper.TransactionMapper;
import com.oquba.walletwatch.models.UserEntity;
import com.oquba.walletwatch.service.TransactionService;
import com.oquba.walletwatch.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.oquba.walletwatch.mapper.TransactionMapper.mapToTransactionGraphData;

@RestController
@RequestMapping("/api")
public class TransactionApiController {
    private final UserService userService;
    private final TransactionService transactionService;

    public TransactionApiController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/transactions", produces = "application/json")
    public List<TransactionGraphData> getTransactionDataForGraph(Principal principal)  {
        UserEntity user = userService.findByUsername(principal.getName());
        Long userId = user.getId();
        List<TransactionDto> transactions = transactionService.getAllTransactions(userId);

        List<TransactionGraphData> graphData = transactions.stream()
                .map(transaction -> mapToTransactionGraphData(transaction))
                .collect(Collectors.toList());

        return graphData;
    }
}
