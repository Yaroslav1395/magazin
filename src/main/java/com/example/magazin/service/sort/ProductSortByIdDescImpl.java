package com.example.magazin.service.sort;

import com.example.magazin.dto.product.ProductForMainDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSortByIdDescImpl implements ProductSort{
    @Override
    public List<ProductForMainDto> sortProduct(List<ProductForMainDto> products) {
        return products.stream()
                .sorted(Comparator.comparing(ProductForMainDto::getId)
                        .reversed())
                .collect(Collectors.toList());
    }
}
