package com.example.magazin.dto.mappers;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.entity.category.Category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
}
