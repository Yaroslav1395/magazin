package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.*;
import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.dto.product.ProductForSingleDto;
import com.example.magazin.dto.review.ReviewDto;
import com.example.magazin.entity.product.Product;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.ProductInOrderCount;
import com.example.magazin.repository.product.ProductRepository;
import com.example.magazin.repository.review.ReviewRepository;
import com.example.magazin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryMapper categoryMapper;
    private CompanyMapper companyMapper;
    private ProductImageMapper productImageMapper;
    private UserMapper userMapper;

    public ProductForSingleDto getProductById(Integer id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        return ProductForSingleDto.builder()
                .id(product.getId())
                .name(product.getName())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .companyDto(companyMapper.toDto(product.getCompany()))
                .categoryDto(categoryMapper.toDto(product.getCategory()))
                .productImageDto(productImageMapper.toDto(product.getProductImage()))
                .reviewDtoList(product.getReviews().stream()
                        .map(review -> ReviewDto.builder()
                                .message(review.getMessage())
                                .dateTime(review.getDateTime())
                                .id(review.getId())
                                .userDto(userMapper.toDto(review.getUser()))
                                .build())
                        .toList())
                .build();
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
    public Page<ProductForMainDto> getAllProductsByCategoryId(Pageable pageable, Integer id) {
        List<ProductForMainDto> products =  productRepository.findByCategoryId(id).stream()
                .map(product -> ProductForMainDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        .productImageDto(productImageMapper.toDto(product.getProductImage()))
                        .categoryDto(categoryMapper.toDto(product.getCategory()))
                        .build())
                .toList();

        final int toIndex = Math.min((pageable.getPageNumber() + 1) * pageable.getPageSize(),
                products.size());
        final int fromIndex = Math.max(toIndex - pageable.getPageSize(), 0);

        return new PageImpl<ProductForMainDto>(
                                products.subList(fromIndex, toIndex),
                                pageable,
                                products.size());
    }

    @Override
    public List<ProductForMainDto> getFourBestSellingProductsByCategory(Integer categoryId) {
        List<Integer> productsId = productRepository.findFourBestSellingProductsByCategory(categoryId).stream()
                .map(ProductInOrderCount::getProductId)
                .toList();

        return productRepository.findAllById(productsId).stream()
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
    public List<ProductForMainDto> getProductsByIdList(List<Integer> productsId){
        return productRepository.findAllById(productsId).stream()
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
