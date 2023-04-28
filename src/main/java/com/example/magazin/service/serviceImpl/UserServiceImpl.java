package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.UserMapper;
import com.example.magazin.dto.mappers.UserRegistrationMapper;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.user.UserRegistrationDto;
import com.example.magazin.entity.user.User;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.user.UserRepository;
import com.example.magazin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserRegistrationMapper userRegistrationMapper;
    public boolean isUserExist(Integer id){
        return userRepository.existsById(id);
    }

    public UserDto getUserById(Integer id){
        User user;
        try {
            user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
        return userMapper.toDto(user);
    }

    public UserDto getUserByName(String lastName){
        User user;
        try {
            user = userRepository.findByLastName(lastName).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
        return userMapper.toDto(user);
    }
    public UserDto getUserByEmail(String email){
        User user;
        try {
            user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
        return userMapper.toDto(user);
    }
    public List<UserDto> getUsersByIds(List<Integer> ids){
        return userRepository.findAllById(ids).stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }
    public List<UserDto> getUsersByEmails(List<String> emails){
        return userRepository.findByEmailIn(emails).stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }
    public List<UserDto> getUsersByNames(List<String> lastNames){
        return userRepository.findByLastNameIn(lastNames).stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }
    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }
    public List<UserDto> getAllUsers(Sort sort){
        return userRepository.findAll(sort).stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }
    public List<UserDto> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable).stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }
    public UserRegistrationDto saveUser(UserRegistrationDto userRegistrationDto){
        User user = userRegistrationMapper.toEntity(userRegistrationDto);
        User saveUser = userRepository.save(user);
        return userRegistrationMapper.toDto(saveUser);
    }
    public List<UserRegistrationDto> saveUsers(List<UserRegistrationDto> usersRegistrationDtoList){
        List<User> users = usersRegistrationDtoList.stream()
                .map(userRegistrationDto -> userRegistrationMapper.toEntity(userRegistrationDto))
                .toList();
        List<User> saveUsers = userRepository.saveAll(users);
        return saveUsers.stream()
                .map(user -> userRegistrationMapper.toDto(user))
                .collect(Collectors.toList());
    }
    public boolean deleteUserById(Integer id){
        User user;
        try{
            user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
            userRepository.deleteById(user.getId());
            return true;
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }
    public void deleteUsersByIds(List<Integer> ids){
        userRepository.deleteAllById(ids);
    }
    public UserDto updateUser(Integer userId, UserDto userDto){
        userRepository.deleteById(userId);
        User user = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(user);
    }
    public Long countUsers(){
        return userRepository.count();
    }

}
