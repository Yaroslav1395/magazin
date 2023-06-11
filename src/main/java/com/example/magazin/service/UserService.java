package com.example.magazin.service;

import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.user.UserRegistrationDto;
import com.example.magazin.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    boolean isUserExist(Integer id);
    UserDto getUserById(Integer id);
    UserDto getUserByName(String name);
    UserDto getUserByEmail(String email);
    List<UserDto> getUsersByIds(List<Integer> ids);
    List<UserDto> getUsersByEmails(List<String> emails);
    List<UserDto> getUsersByNames(List<String> emails);
    List<UserDto> getAllUsers();
    List<UserDto> getAllUsers(Sort sort);
    List<UserDto> getAllUsers(Pageable pageable);
    UserRegistrationDto saveUser(UserRegistrationDto userRegistrationDto);
    List<UserRegistrationDto> saveUsers(List<UserRegistrationDto> usersRegistrationDtoList);
    boolean deleteUserById(Integer id);
    void deleteUsersByIds(List<Integer> ids);
    UserDto updateUser(Integer userId, UserDto userDto);
    Long countUsers();
    boolean existsByEmail(String email);
}
