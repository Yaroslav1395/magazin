package com.example.magazin.repository.product;

import com.example.magazin.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
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

    List<Product> findFirst8ByOrderByReceiptDateDesc();
    List<Product> findByCategoryId(Integer id);

}