package com.example.magazin.repository.user;


import com.example.magazin.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    List<User> findAllByName(List<String> names);
    List<User> findAllByEmail(List<String> emails);

}
