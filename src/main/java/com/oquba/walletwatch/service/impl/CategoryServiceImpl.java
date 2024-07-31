package com.oquba.walletwatch.service.impl;

import com.oquba.walletwatch.dto.CategoryDto;
import com.oquba.walletwatch.models.Category;
import com.oquba.walletwatch.models.Transaction;
import com.oquba.walletwatch.models.UserEntity;
import com.oquba.walletwatch.repository.CategoryRepository;
import com.oquba.walletwatch.repository.TransactionRepository;
import com.oquba.walletwatch.repository.UserRepository;
import com.oquba.walletwatch.security.SecurityUtil;
import com.oquba.walletwatch.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.oquba.walletwatch.mapper.CategoryMapper.mapToCategory;
import static com.oquba.walletwatch.mapper.CategoryMapper.mapToCategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, TransactionRepository transactionRepository,
                               UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Long id = user.getId();
        List<Category> categories = categoryRepository.findAll(id);
        return categories.stream().map((category) -> mapToCategoryDto(category)).collect(Collectors.toList());
    }

    @Override
    public Category saveCategory(CategoryDto categoryDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Category category = mapToCategory(categoryDto);
        category.setCreatedBy(user);
        return categoryRepository.save(category);
    }


    @Override
    public CategoryDto findCategoryByID(long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        return mapToCategoryDto(category);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Category category = mapToCategory(categoryDto);
        category.setCreatedBy(user);
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    /*
    @Override
    public void createCategory(Long transactionId, CategoryDto categoryDto) {
        Transaction transaction = transactionRepository.findById(transactionId);
    }
     */
}
