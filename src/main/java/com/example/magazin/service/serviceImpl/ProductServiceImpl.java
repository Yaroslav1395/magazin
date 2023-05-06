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
import com.example.magazin.service.sort.ProductSortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

        return buildingProductForSingleDtoFromProduct(product);
    }
    public boolean existsById(Integer id){
        return productRepository.existsById(id);
    }
    public Page<ProductForMainDto> getAllProductsPageable(Pageable pageable){
        List<ProductForMainDto> products = buildingListProductForMainDtoFromProducts(productRepository.findAll());
        final int toIndex = Math.min((pageable.getPageNumber() + 1) * pageable.getPageSize(),
                products.size());
        final int fromIndex = Math.max(toIndex - pageable.getPageSize(), 0);

        return new PageImpl<ProductForMainDto>(
                products.subList(fromIndex, toIndex),
                pageable,
                products.size());
    }
    public List<ProductForMainDto> getAll(){
        return buildingListProductForMainDtoFromProducts(productRepository.findAll());
    }

    public ProductForSaveDto save(ProductForSaveDto productForSaveDto){
        Product product = buildingProductFromProductForSaveDto(productForSaveDto);

        Product saveProduct = productRepository.save(product);

        return buildingProductFromProductForSaveDto(saveProduct);
    }
    public List<ProductForSaveDto> saveAll(List<ProductForSaveDto> products){
        List<Product> productList = buildingProductsFromProductsForSave(products);
        List<Product> saveProducts = productRepository.saveAll(productList);
        return buildingProductsForSaveFromProducts(saveProducts);
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
        return buildingListProductForMainDtoFromProducts(productRepository.findMostExpensiveProductInEachCategoryWithLimitFour());
    }

    @Override
    public List<ProductForMainDto> getFirst8ByOrderByReceiptDate() {
        return buildingListProductForMainDtoFromProducts(productRepository.findFirst8ByOrderByReceiptDateDesc());
    }

    @Override
    public Page<ProductForMainDto> getAllProductsByCategoryId(Pageable pageable, Integer id) {
        List<Product> products = productRepository.findByCategoryId(id);
        List<ProductForMainDto> productForMainDtos = buildingListProductForMainDtoFromProducts(products);

        return getPageListOfProducts(pageable, productForMainDtos);
    }
    @Override
    public List<ProductForMainDto> getFourBestSellingProductsByCategory(Integer categoryId) {
        List<Integer> productsId = productRepository.findFourBestSellingProductsByCategory(categoryId).stream()
                .map(ProductInOrderCount::getProductId)
                .toList();

        return buildingListProductForMainDtoFromProducts(productRepository.findAllById(productsId));
    }

    @Override
    public Page<ProductForMainDto> getProductByKeyword(Pageable pageable, String keyword) {
        List<Product> products = productRepository.findProductByKeyword(keyword);
        List<ProductForMainDto> productForMainDtos = buildingListProductForMainDtoFromProducts(products);
        return getPageListOfProducts(pageable, productForMainDtos);
    }

    @Override
    public Page<ProductForMainDto> getProductByKeywordWithPriceLimit(Pageable pageable, String keyword, Double min, Double max) {
        BigDecimal minPrice = BigDecimal.valueOf(min);
        BigDecimal maxPrice = BigDecimal.valueOf(max);

        List<Product> products = productRepository.findProductByKeywordWithPriceLimit(minPrice, maxPrice, keyword);
        List<ProductForMainDto> productForMainDtos = buildingListProductForMainDtoFromProducts(products);
        return getPageListOfProducts(pageable, productForMainDtos);
    }

    @Override
    public Page<ProductForMainDto> getProductByCategoryIdWithPriceLimit(Pageable pageable, Integer categoryId, Double min, Double max) {
        BigDecimal minPrice = BigDecimal.valueOf(min);
        BigDecimal maxPrice = BigDecimal.valueOf(max);

        List<Product> products = productRepository.findProductByCategoryIdWithPriceLimit(minPrice, maxPrice, categoryId);
        List<ProductForMainDto> productForMainDtos = buildingListProductForMainDtoFromProducts(products);
        return getPageListOfProducts(pageable, productForMainDtos);
    }

    @Override
    public Double getMaxPriceInAllProducts() {
        return productRepository.findFirstByOrderByPriceDesc().getPrice().doubleValue();
    }

    @Override
    public Double getMinPriceInAllProducts() {
        return productRepository.findFirstByOrderByPriceAsc().getPrice().doubleValue();
    }

    @Override
    public Double getMaxProductPriceInCategory(Integer categoryId) {
        return productRepository.findFirstByCategoryIdOrderByPriceDesc(categoryId).getPrice().doubleValue();
    }

    @Override
    public Double getMinProductPriceInCategory(Integer categoryId) {
        return productRepository.findFirstByCategoryIdOrderByPriceAsc(categoryId).getPrice().doubleValue();
    }

    @Override
    public Double getMaxProductPriceByKeyword(String keyword) {
        return productRepository.findMaxPriceOfProductByKeyword(keyword).getPrice().doubleValue();
    }

    @Override
    public Double getMinProductPriceByKeyword(String keyword) {
        return productRepository.findMinPriceOfProductByKeyword(keyword).getPrice().doubleValue();
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
    @Override
    public Pageable createPageable(String sortField, String sortDir, Integer pageNumber){
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        return PageRequest.of(pageNumber, 6, sort);
    }




    private Page<ProductForMainDto> getPageListOfProducts(Pageable pageable, List<ProductForMainDto> products){
        final int toIndex = Math.min((pageable.getPageNumber() + 1) * pageable.getPageSize(),
                products.size());
        final int fromIndex = Math.max(toIndex - pageable.getPageSize(), 0);
        List<ProductForMainDto> sortedProducts;

        try {
            sortedProducts = ProductSortType.getProductSortType(pageable.getSort().toString())
                                                                                .getProductSort().sortProduct(products);

        }catch (RuntimeException e){
            e.printStackTrace();
            return new PageImpl<ProductForMainDto>(
                    products.subList(fromIndex, toIndex),
                    pageable,
                    products.size());
        }

        return new PageImpl<ProductForMainDto>(
                sortedProducts.subList(fromIndex, toIndex),
                pageable,
                products.size());
    }

    private List<ProductForMainDto> buildingListProductForMainDtoFromProducts(List<Product> products){
        return products.stream()
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
    private ProductForMainDto buildingProductForMainDtoFromProduct(Product product){
        return ProductForMainDto.builder()
                .id(product.getId())
                .name(product.getName())
                .title(product.getTitle())
                .price(product.getPrice())
                .productImageDto(productImageMapper.toDto(product.getProductImage()))
                .categoryDto(categoryMapper.toDto(product.getCategory()))
                .build();
    }
    private ProductForSingleDto buildingProductForSingleDtoFromProduct(Product product){
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
    private List<Product> buildingProductsFromProductsForSave(List<ProductForSaveDto> products){
        return products.stream()
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
    }

    private List<ProductForSaveDto> buildingProductsForSaveFromProducts(List<Product> products){
        return products.stream()
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


    private Product buildingProductFromProductForSaveDto(ProductForSaveDto productForSaveDto){
        return Product.builder()
                .name(productForSaveDto.getName())
                .title(productForSaveDto.getTitle())
                .description(productForSaveDto.getDescription())
                .amount(1)
                .price(productForSaveDto.getPrice())
                .productImage(productImageMapper.toEntity(productForSaveDto.getProductImageDto()))
                .category(categoryMapper.toEntity(productForSaveDto.getCategoryDto()))
                .company(companyMapper.toEntity(productForSaveDto.getCompanyDto()))
                .build();
    }

    private ProductForSaveDto buildingProductFromProductForSaveDto(Product saveProduct){
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
}
