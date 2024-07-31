package com.oquba.walletwatch.controller;

import com.oquba.walletwatch.dto.CategoryDto;
import com.oquba.walletwatch.dto.TransactionDto;
import com.oquba.walletwatch.models.Transaction;
import com.oquba.walletwatch.models.UserEntity;
import com.oquba.walletwatch.security.SecurityUtil;
import com.oquba.walletwatch.service.CategoryService;
import com.oquba.walletwatch.service.TransactionService;
import com.oquba.walletwatch.service.UserService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class TransactionController {
    private TransactionService transactionService;
    private CategoryService categoryService;
    private UserService userService;

    public TransactionController(TransactionService transactionService, CategoryService categoryService,
                                 UserService userService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        Long id = user.getId();
        List<TransactionDto> transactions = transactionService.getAllTransactions(id);
        model.addAttribute(transactions);
        model.addAttribute(user);
        return "dashboard";
    }

    @GetMapping("/transactions")
    public String listTransactions(Model model) {
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        Long id = user.getId();
        List<TransactionDto> transactions = transactionService.getAllTransactions(id);
        model.addAttribute("transactions", transactions);
        model.addAttribute("user", user);

        return "transactions-list";
    }

    @GetMapping("/transactions/new")
    public String createTransactionForm(Model model) {
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "transactions-create";
    }

    @PostMapping("/transactions/new")
    public String saveTransaction(@Valid @ModelAttribute("transaction") @DateTimeFormat (pattern= "yyyy-MM-dd") TransactionDto transactionDto,
                                  BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("transaction", transactionDto);
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "transactions-create";
        }
        //transaction.setIsDeposit();
        transactionService.saveTransaction(transactionDto);
        return "redirect:/transactions";
    }

    @GetMapping("/transactions/{transactionId}/edit")
    public String editTransactionForm(@PathVariable("transactionId") Long transactionId, Model model) {
        TransactionDto transaction = transactionService.findTransactionByID(transactionId);
        model.addAttribute("transaction", transaction);
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "transactions-edit";
    }

    @PostMapping("/transactions/{transactionId}/edit")
    public String updateTransaction(@PathVariable("transactionId") Long transactionId,
                                    @Valid @ModelAttribute("transaction") TransactionDto transaction,
                                    BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("transaction", transaction);
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "transactions-edit";
        }
        transaction.setId(transactionId);
        transactionService.updateTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/transactions/{transactionId}/delete")
    public String deleteTransaction(@PathVariable("transactionId")Long transactionId) {
        transactionService.delete(transactionId);
        return "redirect:/transactions";
    }

    @GetMapping("/transactions/search")
    public String searchTransaction(@RequestParam(value = "query") String query, Model model, Principal principal) {
        String username = principal.getName();
        UserEntity user = userService.findByUsername(username);
        if (user != null) {
            Long userId = user.getId();
            List<TransactionDto> transactions = transactionService.searchCategoryByUserId(query, userId);
            model.addAttribute("transactions", transactions);
            model.addAttribute("user", user);
        }
        return "transactions-list";
    }
}
