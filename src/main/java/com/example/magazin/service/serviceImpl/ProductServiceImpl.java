package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.CategoryMapper;
import com.example.magazin.dto.mappers.ProductImageMapper;
import com.example.magazin.dto.mappers.ProductMapper;
import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.entity.product.Product;
import com.example.magazin.entity.productImage.ProductImage;
import com.example.magazin.entity.review.Review;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.product.ProductRepository;
import com.example.magazin.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private CategoryMapper categoryMapper;
    private ProductImageMapper productImageMapper;

    public Product getProductById(Integer id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));
    }
    public boolean existsById(Integer id){
        return productRepository.existsById(id);
    }
    public Page<Product> getAllPageable(Pageable pageable){
        return productRepository.findAll(pageable);
    }
    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
    public List<Product> saveAll(List<Product> products){
        return  productRepository.saveAll(products);
    }
    public boolean delete(Product product){
        productRepository.delete(product);
        return !existsById(product.getId());
    }

    @Override
    public List<ProductForMainDto> getMostExpensiveProductInEachCategoryWithLimitFour() {
        return productRepository.findMostExpensiveProductInEachCategoryWithLimitFour().stream()
                .map(product -> ProductForMainDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        .productImageDto(productImageMapper.toDto(product.getProductImage()))
                        .categoryDto(categoryMapper.toDto(product.getCategory()))
                        .build())
                .toList();
    }

    @Override
    public List<ProductForMainDto> getFirst8ByOrderByReceiptDate() {
        return productRepository.findFirst8ByOrderByReceiptDateDesc().stream()
                .map(product -> ProductForMainDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        .productImageDto(productImageMapper.toDto(product.getProductImage()))
                        .categoryDto(categoryMapper.toDto(product.getCategory()))
                        .build())
                .toList();
    }

    @Override
    public List<ProductForMainDto> getAllProductsByCategoryId(Integer id) {
        return productRepository.findByCategoryId(id).stream()
                .map(product -> ProductForMainDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        .productImageDto(productImageMapper.toDto(product.getProductImage()))
                        .categoryDto(categoryMapper.toDto(product.getCategory()))
                        .build())
                .toList();
    }

}
