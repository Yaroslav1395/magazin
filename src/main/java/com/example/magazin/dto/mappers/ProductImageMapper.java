package com.example.magazin.dto.mappers;

import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.entity.productImage.ProductImage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    List<ProductImage> toEntity(List<ProductImageDto> productImageDto);
    List<ProductImageDto> toDto(List<ProductImage> productImage);
}
