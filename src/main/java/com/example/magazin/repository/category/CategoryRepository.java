package com.example.magazin.repository.category;

import com.example.magazin.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    boolean existsByName(String name);
    Optional<Category> findByName(String name);
    List<Category> findByNameIn(List<String> names);
}
