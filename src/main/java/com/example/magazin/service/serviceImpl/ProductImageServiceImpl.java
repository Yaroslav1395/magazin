package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.entity.product.Product;
import com.example.magazin.entity.productImage.ProductImage;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.product.ProductRepository;
import com.example.magazin.repository.productImage.ProductImageRepository;
import com.example.magazin.service.ProductImageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.testng.internal.collections.Ints;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private ProductImageRepository productImageRepository;

    @Override
    public ProductImageDto save(ProductImageDto productImageDto) {
        return null;
    }

    @Override
    public List<ProductImageDto> saveAll(List<ProductImageDto> productImageDtoList, Product product) {
        List<ProductImage> productImageList = buildProductImagesFromProductImagesDto(productImageDtoList, product);
        List<ProductImage> productImagesSave = new ArrayList<>();
        productImageList.forEach(productImage -> {
            ProductImage productImageSave = productImageRepository.save(productImage);
            productImagesSave.add(productImageSave);

        });

        return buildListOfProductImageDtoFromListOfProductImage(productImagesSave);
    }

    private List<ProductImageDto> buildListOfProductImageDtoFromListOfProductImage(List<ProductImage> productImages){
        return productImages.stream().map(productImage -> ProductImageDto.builder()
                        .id(productImage.getId())
                        .filePath(productImage.getFilePath())
                        .src(productImage.getSrc())
                        .build())
                .collect(Collectors.toList());
    }
    private List<ProductImage> buildProductImagesFromProductImagesDto(List<ProductImageDto> productImageDtoList, Product product){
        return productImageDtoList.stream().map(productImageDto -> ProductImage.builder()
                        .product(product)
                        .src(productImageDto.getSrc())
                        .filePath(productImageDto.getFilePath())
                        .build())
                .collect(Collectors.toList());
    }
}
