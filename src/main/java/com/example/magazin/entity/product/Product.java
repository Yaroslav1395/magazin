package com.example.magazin.entity.product;

import com.example.magazin.entity.category.Category;
import com.example.magazin.entity.company.Company;
import com.example.magazin.entity.order.Order;
import com.example.magazin.entity.productImage.ProductImage;
import com.example.magazin.entity.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 200, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String description;
    @Column(length = 100, nullable = false)
    private BigDecimal price;
    @Column(length = 100, nullable = false)
    private Integer amount;
    @Column(name = "receipt_date", length = 200, nullable = false)
    private LocalDateTime receiptDate;
    @OneToOne
    @JoinColumn(name = "product_image_id")
    private ProductImage productImage;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @OrderBy("dateTime ASC")
    private List<Review> reviews = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Order> orders = new ArrayList<>();
}
