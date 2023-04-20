package com.example.magazin.service;

import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
   Product getProductById(Integer id);

   boolean existsById(Integer id);
   Page<Product> getAllPageable(Pageable pageable);
   List<Product> getAll();
   Product save(Product product);
   List<Product> saveAll(List<Product> products);
   boolean delete(Product product);


   List<ProductForMainDto> getMostExpensiveProductInEachCategoryWithLimitFour();
   List<ProductForMainDto> getFirst8ByOrderByReceiptDate();
}
