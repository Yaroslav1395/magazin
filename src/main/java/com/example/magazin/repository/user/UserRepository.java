package com.example.magazin.repository.user;


import com.example.magazin.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByLastName(String lastName);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByLastNameIn(List<String> lastNames);
    List<User> findByEmailIn(List<String> emails);

}
