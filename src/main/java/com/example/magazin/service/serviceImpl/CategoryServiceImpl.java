package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.mappers.CategoryMapper;
import com.example.magazin.entity.category.Category;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.category.CategoryRepository;
import com.example.magazin.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public boolean isCategoryExist(Integer id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public boolean isCategoryExist(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category;
        try {
            category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            return categoryMapper.toDto(category);
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        Category category;
        try {
            category = categoryRepository.findByName(name)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            return categoryMapper.toDto(category);
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CategoryDto> getCategoriesByIds(List<Integer> ids) {
        List<Category> categoryList = categoryRepository.findAllById(ids);
        return categoryList.stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoriesByNames(List<String> names) {
        List<Category> categoryList = categoryRepository.findByNameIn(names);
        return categoryList.stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategories(Sort sort) {
        return categoryRepository.findAll(sort).stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.save(categoryMapper.toEntity(categoryDto));
        return  categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> saveCategories(List<CategoryDto> categoryDtoList) {
        List<Category> categoryList = categoryDtoList.stream()
                .map(categoryDto -> categoryMapper.toEntity(categoryDto))
                .toList();
        return categoryRepository.saveAll(categoryList).stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        Category category;
        try {
            category = categoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return false;
        }
        categoryRepository.deleteById(category.getId());
        return true;
    }

    @Override
    public void deleteCategoriesByIds(List<Integer> ids) {
        categoryRepository.deleteAllById(ids);
    }

    @Override
    public CategoryDto updateUser(Integer categoryId, CategoryDto categoryDto) {
        Category category;
        try {
            category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            categoryRepository.deleteById(category.getId());
            return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDto)));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long countCategories() {
        return categoryRepository.count();
    }
}
