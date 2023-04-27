package com.example.magazin.repository.product;

import com.example.magazin.dto.product.ProductForMainDto;
import com.example.magazin.entity.product.Product;
import com.example.magazin.repository.ProductInOrderCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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



}