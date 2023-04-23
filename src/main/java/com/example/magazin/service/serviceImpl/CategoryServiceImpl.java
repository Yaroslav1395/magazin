package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.category.CategoryDto;
import com.example.magazin.dto.mappers.CategoryMapper;
import com.example.magazin.repository.category.CategoryRepository;
import com.example.magazin.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.toDto(category))
                .collect(Collectors.toList());
    }
}
