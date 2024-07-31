package com.oquba.walletwatch.controller;

import com.oquba.walletwatch.dto.CategoryDto;
import com.oquba.walletwatch.dto.TransactionDto;
import com.oquba.walletwatch.models.Category;
import com.oquba.walletwatch.service.CategoryService;
import com.oquba.walletwatch.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private CategoryService categoryService;
    private UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/category/new")
    public String createCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category-create";
    }
    @PostMapping("/category/new")
    public String saveCategory(@Valid @ModelAttribute("category")CategoryDto categoryDto,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("category", categoryDto);
            return "category-create";
        }
        categoryService.saveCategory(categoryDto);
        return "redirect:/transactions";
    }
}
