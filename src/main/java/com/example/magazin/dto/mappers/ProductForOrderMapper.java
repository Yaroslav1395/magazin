package com.example.magazin.dto.mappers;

import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.entity.product.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductForOrderMapper {
    Product toEntity(ProductForOrderDto productForOrderDto);
    ProductForOrderDto toDto(Product product);
}
