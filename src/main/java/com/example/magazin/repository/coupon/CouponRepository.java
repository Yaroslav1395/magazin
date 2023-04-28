package com.example.magazin.repository.coupon;

import com.example.magazin.entity.coupon.Coupon;
import com.example.magazin.entity.product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Optional<Coupon> findByName(String name);
    List<Coupon> findByNameIn(List<String> names);
}
