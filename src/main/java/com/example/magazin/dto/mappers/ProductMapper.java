package com.example.magazin.dto.mappers;

import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.entity.product.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductForMainDto productDto);
    ProductForMainDto toDto(Product product);
}
