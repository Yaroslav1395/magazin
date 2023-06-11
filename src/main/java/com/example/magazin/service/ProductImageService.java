package com.example.magazin.service;

import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.entity.product.Product;

import java.util.List;

public interface ProductImageService {
    ProductImageDto save(ProductImageDto productImageDto);
    List<ProductImageDto> saveAll(List<ProductImageDto> productImageDtoList, Product product);
}
