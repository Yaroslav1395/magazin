package com.example.magazin.repository.role;

import com.example.magazin.entity.review.Review;
import com.example.magazin.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
     Optional<Role> findByRole(String role);
     @Query(nativeQuery = true, value = "select * from roles\n" +
             "inner join users_roles as ru\n" +
             "on roles.id = ru.role_id\n" +
             "where ru.user_id = ?;")
     List<Role> findRoleByUserId(Integer userId);
}
