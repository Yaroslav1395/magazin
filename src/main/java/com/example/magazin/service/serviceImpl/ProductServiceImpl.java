package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.*;
import com.example.magazin.dto.product.*;
import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.dto.review.ReviewDto;
import com.example.magazin.entity.category.Category;
import com.example.magazin.entity.company.Company;
import com.example.magazin.entity.product.Product;
import com.example.magazin.entity.productImage.ProductImage;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.CompanyRepository;
import com.example.magazin.repository.ProductInOrderCount;
import com.example.magazin.repository.category.CategoryRepository;
import com.example.magazin.repository.product.ProductRepository;
import com.example.magazin.service.ProductImageService;
import com.example.magazin.service.ProductService;
import com.example.magazin.service.sort.ProductSortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private CompanyRepository companyRepository;
    private ProductImageService productImageService;
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

    @Override
    public ProductForOrderDto getProductForOrderById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return buildingProductForOrderDtoFromProduct(product);
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

    @Override
    public List<ProductForSaveDto> getAllProductsAsProductForSaveDto() {
        return buildingProductsForSaveFromProducts(productRepository.findAll());
    }

    @Override
    public List<ProductForSaveDto> getAllProductsAsProductForSaveDtoByCategoryId(Integer categoryId) {
        return buildingProductsForSaveFromProducts(productRepository.findByCategoryId(categoryId));
    }

    @Override
    public List<ProductForSaveDto> getProductsAsProductForSaveDtoByKeyword(String keyword) {
        return buildingProductsForSaveFromProducts(productRepository.findProductByKeyword(keyword));
    }

    @Override
    public List<ProductForSaveDto> getProductsAsProductForSaveDtoByCompanyId(Integer companyId) {
        return buildingProductsForSaveFromProducts(productRepository.findByCompanyId(companyId));
    }
    @Transactional
    public ProductForSaveDto save(ProductNewDto productNewDto, List<ProductImageDto> productImagesDto){
        Product product = buildingProductFromProductForSaveDto(productNewDto);
        List<ProductImage> productImages = productImagesDto.stream().map(productImageDto -> ProductImage.builder()
                .product(product)
                .src(productImageDto.getSrc())
                .filePath(productImageDto.getFilePath())
                .build()).toList();
        product.setProductImages(productImages);

        Product productSave = productRepository.save(product);
        //List<ProductImageDto> productImageDtoList = productImageService.saveAll(productImagesDto, product);

        return null;//buildingProductFromProductForSaveDto(productSave, productImageDtoList);
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
    public List<ProductForMainDto> getProductsByIdList(Set<Integer> productsIds){
        return productRepository.findAllById(productsIds).stream()
                .map(product -> ProductForMainDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        //.productImageDto(productImageMapper.toDto(product.getProductImage()))
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
                        .productImagesDto(product.getProductImages().stream().map(productImage -> ProductImageDto.builder()
                                        .src(productImage.getSrc())
                                        .filePath(productImage.getFilePath())
                                        .build())
                                .collect(Collectors.toList()))
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
                .productImagesDto(productImageMapper.toDto(product.getProductImages()))
                .categoryDto(categoryMapper.toDto(product.getCategory()))
                .build();
    }

    private ProductForOrderDto buildingProductForOrderDtoFromProduct(Product product){
        System.out.println(product.getId());
        System.out.println(product.getAmount());
        return ProductForOrderDto.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategory().getId())
                .price(product.getPrice())
                .productImages(product.getProductImages().stream()
                        .map(productImage -> ProductImageDto.builder()
                                .src(productImage.getSrc())
                                .filePath(productImage.getFilePath())
                                .build())
                        .collect(Collectors.toList()))
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
                .productImagesDto(product.getProductImages().stream()
                        .map(productImage -> ProductImageDto.builder()
                                .src(productImage.getSrc())
                                .filePath(productImage.getFilePath())
                                .build())
                        .collect(Collectors.toList()))
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
                        .productImages(productImageMapper.toEntity(productForSaveDto.getProductImagesDto()))
                        .category(categoryMapper.toEntity(productForSaveDto.getCategoryDto()))
                        .company(companyMapper.toEntity(productForSaveDto.getCompanyDto()))
                        .build())
                .toList();
    }

    private List<ProductForSaveDto> buildingProductsForSaveFromProducts(List<Product> products){
        return products.stream()
                .map(saveProduct -> ProductForSaveDto.builder()
                        .id(saveProduct.getId())
                        .name(saveProduct.getName())
                        .title(saveProduct.getTitle())
                        .description(saveProduct.getDescription())
                        .price(saveProduct.getPrice())
                        .productImagesDto(productImageMapper.toDto(saveProduct.getProductImages()))
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
                .productImages(productImageMapper.toEntity(productForSaveDto.getProductImagesDto()))
                .category(categoryMapper.toEntity(productForSaveDto.getCategoryDto()))
                .company(companyMapper.toEntity(productForSaveDto.getCompanyDto()))
                .build();
    }

    private Product buildingProductFromProductForSaveDto(ProductNewDto productNewDto){
        Category category = categoryRepository.findById(productNewDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Company company = companyRepository.findById(productNewDto.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        return Product.builder()
                .name(productNewDto.getName())
                .title(productNewDto.getTitle())
                .description(productNewDto.getDescription())
                .amount(1)
                .price(productNewDto.getPrice())
                .category(category)
                .company(company)
                .receiptDate(LocalDateTime.now())
                .build();
    }

    private ProductForSaveDto buildingProductFromProductForSaveDto(Product saveProduct, List<ProductImageDto> productImageDtoList){
        return ProductForSaveDto.builder()
                .id(saveProduct.getId())
                .name(saveProduct.getName())
                .title(saveProduct.getTitle())
                .description(saveProduct.getDescription())
                .price(saveProduct.getPrice())
                .productImagesDto(productImageDtoList)
                .categoryDto(categoryMapper.toDto(saveProduct.getCategory()))
                .companyDto(companyMapper.toDto(saveProduct.getCompany()))
                .build();
    }
}
