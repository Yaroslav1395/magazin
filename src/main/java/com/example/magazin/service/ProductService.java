package com.example.magazin.service;

import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.dto.product.ProductForSaveDto;
import com.example.magazin.dto.product.ProductForSingleDto;
import com.example.magazin.entity.product.Product;
import com.example.magazin.repository.ProductInOrderCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
   ProductForSingleDto getProductById(Integer id);
   boolean existsById(Integer id);
   Page<ProductForMainDto> getAllProductsPageable(Pageable pageable);
   List<ProductForMainDto> getAll();
   ProductForSaveDto save(ProductForSaveDto productForSaveDto);
   List<ProductForSaveDto> saveAll(List<ProductForSaveDto> products);
   boolean deleteById(Integer id);
   List<ProductForMainDto> getProductsByIdList(List<Integer> productsId);
   List<ProductForMainDto> getMostExpensiveProductInEachCategoryWithLimitFour();
   List<ProductForMainDto> getFirst8ByOrderByReceiptDate();
   Page<ProductForMainDto> getAllProductsByCategoryId(Pageable pageable, Integer id);
   List<ProductForMainDto> getFourBestSellingProductsByCategory(Integer categoryId);
   Page<ProductForMainDto> getProductByKeyword(Pageable pageable, String keyword);
   Page<ProductForMainDto> getProductByKeywordWithPriceLimit(Pageable pageable, String keyword, Double min, Double max);
   Page<ProductForMainDto> getProductByCategoryIdWithPriceLimit(Pageable pageable, Integer categoryId, Double min, Double max);
   Double getMaxPriceInAllProducts();
   Double getMinPriceInAllProducts();
   Double getMaxProductPriceInCategory(Integer categoryId);
   Double getMinProductPriceInCategory(Integer categoryId);
   Double getMaxProductPriceByKeyword(String keyword);
   Double getMinProductPriceByKeyword(String keyword);
   Pageable createPageable(String sortField, String sortDir, Integer pageNumber);
}
