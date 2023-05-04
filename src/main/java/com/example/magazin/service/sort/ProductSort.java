package com.example.magazin.service.sort;


import com.example.magazin.dto.product.ProductForMainDto;

import java.util.List;

public interface ProductSort {
    List<ProductForMainDto> sortProduct(List<ProductForMainDto> products);
}
