package com.example.magazin.service;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.user.UserRegistrationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

public interface CategoryService {
    boolean isCategoryExist(Integer id);
    boolean isCategoryExist(String name);
    CategoryDto getCategoryById(Integer id);
    CategoryDto getCategoryByName(String name);
    List<CategoryDto> getCategoriesByIds(List<Integer> ids);
    List<CategoryDto> getCategoriesByNames(List<String> names);
    List<CategoryDto> getAllCategories();
    List<CategoryDto> getAllCategories(Sort sort);
    CategoryDto saveCategory(CategoryDto categoryDto);
    List<CategoryDto> saveCategories(List<CategoryDto> categoryDtoList);
    boolean deleteCategoryById(Integer id);
    void deleteCategoriesByIds(List<Integer> ids);
    CategoryDto updateUser(Integer categoryId, CategoryDto categoryDto);
    Long countCategories();
}
