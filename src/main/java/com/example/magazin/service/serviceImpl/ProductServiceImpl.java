package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.*;
import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.dto.product.ProductForSaveDto;
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
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryMapper categoryMapper;
    private CompanyMapper companyMapper;
    private ProductImageMapper productImageMapper;
    private UserMapper userMapper;

    public ProductForSingleDto getProductById(Integer id){
        Product product;
        try {
            product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }

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
    public Page<ProductForMainDto> getAllProductsPageable(Pageable pageable){
        List<ProductForMainDto> products =  productRepository.findAll().stream()
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
    public List<ProductForMainDto> getAll(){
        return productRepository.findAll().stream()
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

    public ProductForSaveDto save(ProductForSaveDto productForSaveDto){
        Product product = Product.builder()
                .name(productForSaveDto.getName())
                .title(productForSaveDto.getTitle())
                .description(productForSaveDto.getDescription())
                .amount(1)
                .price(productForSaveDto.getPrice())
                .productImage(productImageMapper.toEntity(productForSaveDto.getProductImageDto()))
                .category(categoryMapper.toEntity(productForSaveDto.getCategoryDto()))
                .company(companyMapper.toEntity(productForSaveDto.getCompanyDto()))
                .build();

        Product saveProduct = productRepository.save(product);

        return ProductForSaveDto.builder()
                .name(saveProduct.getName())
                .title(saveProduct.getTitle())
                .description(saveProduct.getDescription())
                .price(saveProduct.getPrice())
                .productImageDto(productImageMapper.toDto(saveProduct.getProductImage()))
                .categoryDto(categoryMapper.toDto(saveProduct.getCategory()))
                .companyDto(companyMapper.toDto(saveProduct.getCompany()))
                .build();
    }
    public List<ProductForSaveDto> saveAll(List<ProductForSaveDto> products){
        List<Product> productList = products.stream()
                .map(productForSaveDto -> Product.builder()
                        .name(productForSaveDto.getName())
                        .title(productForSaveDto.getTitle())
                        .description(productForSaveDto.getDescription())
                        .amount(1)
                        .price(productForSaveDto.getPrice())
                        .productImage(productImageMapper.toEntity(productForSaveDto.getProductImageDto()))
                        .category(categoryMapper.toEntity(productForSaveDto.getCategoryDto()))
                        .company(companyMapper.toEntity(productForSaveDto.getCompanyDto()))
                        .build())
                .toList();
        List<Product> saveProducts = productRepository.saveAll(productList);
        return saveProducts.stream()
                .map(saveProduct -> ProductForSaveDto.builder()
                    .name(saveProduct.getName())
                    .title(saveProduct.getTitle())
                    .description(saveProduct.getDescription())
                    .price(saveProduct.getPrice())
                    .productImageDto(productImageMapper.toDto(saveProduct.getProductImage()))
                    .categoryDto(categoryMapper.toDto(saveProduct.getCategory()))
                    .companyDto(companyMapper.toDto(saveProduct.getCompany()))
                    .build())
                .collect(Collectors.toList());
    }
    public boolean deleteById(Integer id){
        Product product;
        try {
            product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));
            productRepository.deleteById(id);
            return true;
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return false;
        }
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
