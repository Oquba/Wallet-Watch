package com.oquba.walletwatch.service;

import com.oquba.walletwatch.dto.CategoryDto;
import com.oquba.walletwatch.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    // void createCategory(Long categoryId, CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    Category saveCategory(CategoryDto category);

    CategoryDto findCategoryByID(long categoryId);

    void updateCategory(CategoryDto categoryDto);

    void delete(Long categoryId);
}
