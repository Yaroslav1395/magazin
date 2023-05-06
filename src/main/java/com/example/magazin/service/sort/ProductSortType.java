package com.example.magazin.service.sort;

public enum ProductSortType {
    PRODUCT_SORT_BY_NAME_ASC("name: ASC", new ProductSortByNameAscImpl()),
    PRODUCT_SORT_BY_NAME_DESC("name: DESC", new ProductSortByNameDescImpl()),
    PRODUCT_SORT_BY_PRICE_ASC("price: ASC", new ProductSortByPriceAscImpl()),
    PRODUCT_SORT_BY_PRICE_DESC("price: DESC", new ProductSortByPriceDescImpl()),
    PRODUCT_SORT_BY_ID_ASC("id: ASC", new ProductSortByIdAscImpl()),
    PRODUCT_SORT_BY_ID_DESC("id: DESC", new ProductSortByIdDescImpl());

    private String sortParam;
    private ProductSort productSort;

    ProductSortType(String sortParam, ProductSort productSort) {
        this.sortParam = sortParam;
        this.productSort = productSort;
    }

    public ProductSort getProductSort() {
        return productSort;
    }

    public static ProductSortType getProductSortType(String sortParam){
        for(ProductSortType productSortType : ProductSortType.values()){
            if(productSortType.sortParam.equals(sortParam)){
                return productSortType;
            }
        }
        throw new RuntimeException("Sort service for this sort parameter not found");
    }
}
