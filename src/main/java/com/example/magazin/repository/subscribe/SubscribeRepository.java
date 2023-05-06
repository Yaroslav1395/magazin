package com.example.magazin.repository.subscribe;

import com.example.magazin.entity.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
    Optional<Subscribe> findByEmail(String email);
    boolean existsByEmail(String email);
}
