package com.example.magazin.repository.product;

import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.entity.product.Product;
import com.example.magazin.repository.ProductInOrderCount;
import com.example.magazin.repository.ProductPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM (\n" +
            "(SELECT * FROM products as p WHERE p.category_id = 2 ORDER BY p.price DESC limit 4)\n" +
            "UNION \n" +
            "(SELECT * FROM products as p WHERE p.category_id = 3 ORDER BY p.price DESC limit 4)\n" +
            "UNION\n" +
            "(SELECT * FROM products as p WHERE p.category_id = 4 ORDER BY p.price DESC limit 4)\n" +
            "UNION\n" +
            "(SELECT * FROM products as p WHERE p.category_id = 5 ORDER BY p.price DESC limit 4)\n" +
            "UNION\n" +
            "(SELECT * FROM products as p WHERE p.category_id = 6 ORDER BY p.price DESC limit 4)\n" +
            ") as s;")
    List<Product> findMostExpensiveProductInEachCategoryWithLimitFour();

    @Query(nativeQuery = true, value = "select \n" +
            "\tp.id as productId,\n" +
            "    count(p.id) as amount \n" +
            "from products as p\n" +
            "inner join categories as c on c.id = p.category_id\n" +
            "inner join orders_products as op on op.product_id = p.id\n" +
            "inner join orders as o on op.order_id = o.id\n" +
            "where c.id  = ?1\n" +
            "group by productId\n" +
            "order by amount desc\n" +
            "limit 4;")
    List<ProductInOrderCount> findFourBestSellingProductsByCategory(Integer categoryId);
    List<Product> findFirst8ByOrderByReceiptDateDesc();
    List<Product> findByCategoryId(Integer id);
    List<Product> findByCompanyId(Integer id);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, p.category.name, p.company.name) LIKE %?1%")
    List<Product> findProductByKeyword(String keyword);
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.price BETWEEN :min AND :max")
    List<Product> findProductByCategoryIdWithPriceLimit(
            @Param("min") BigDecimal min, @Param("max") BigDecimal max, @Param("categoryId") Integer categoryId);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, p.category.name, p.company.name) " +
            "LIKE %:keyword% AND p.price BETWEEN :min AND :max")
    List<Product> findProductByKeywordWithPriceLimit(
            @Param("min") BigDecimal min, @Param("max") BigDecimal max, @Param("keyword") String keyword);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, p.category.name, p.company.name) " +
            "LIKE %:keyword% ORDER BY p.price DESC LIMIT 1")
    ProductPrice findMaxPriceOfProductByKeyword(@Param("keyword") String keyword);
    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, p.category.name, p.company.name) " +
            "LIKE %:keyword% ORDER BY p.price ASC LIMIT 1")
    ProductPrice findMinPriceOfProductByKeyword(@Param("keyword") String keyword);
    ProductPrice findFirstByOrderByPriceDesc();
    ProductPrice findFirstByOrderByPriceAsc();

    ProductPrice findFirstByCategoryIdOrderByPriceDesc(Integer id);
    ProductPrice findFirstByCategoryIdOrderByPriceAsc(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE products\n" +
            "SET amount = :amount\n" +
            "WHERE products.id = :productId")
    void updateProductAmountById(@Param("productId") Integer productId, @Param("amount") Integer amount);


}