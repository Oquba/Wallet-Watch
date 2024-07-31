package com.oquba.walletwatch.mapper;

import com.oquba.walletwatch.dto.CategoryDto;
import com.oquba.walletwatch.models.Category;

public class CategoryMapper {
    public static Category mapToCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .createdBy(categoryDto.getCreatedBy())
                .build();
        return category;
    }

    public static CategoryDto mapToCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .createdBy(category.getCreatedBy())
                .build();
        return categoryDto;
    }
}
