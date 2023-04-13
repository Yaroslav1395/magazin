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
    @Column(name = "image_one", length = 300, nullable = false)
    private String imageOne;
    @Column(name = "image_two", length = 300, nullable = false)
    private String imageTwo;
    @Column(name = "image_three", length = 300, nullable = false)
    private String imageThree;
    @Column(name = "image_four", length = 300, nullable = false)
    private String imageFour;
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;
}
