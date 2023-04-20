package com.example.magazin.dto.mappers;

import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.entity.productImage.ProductImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    ProductImage toEntity(ProductImageDto productImageDto);
    ProductImageDto toDto(ProductImage productImage);
}
