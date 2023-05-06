package com.example.magazin.service.sort;

import com.example.magazin.dto.product.ProductForMainDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSortByNameAscImpl implements ProductSort{
    @Override
    public List<ProductForMainDto> sortProduct(List<ProductForMainDto> products) {
        return products.stream()
                .sorted(Comparator.comparing(ProductForMainDto::getName))
                .collect(Collectors.toList());
    }
}
