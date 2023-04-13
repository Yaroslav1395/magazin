package com.example.magazin.entity.productImage;

import com.example.magazin.entity.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product_images")
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 300, nullable = false)
    private String imageOne;
    @Column(length = 300, nullable = false)
    private String imageTwo;
    @Column(length = 300, nullable = false)
    private String imageThree;
    @Column(length = 300, nullable = false)
    private String imageFour;
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;
}
