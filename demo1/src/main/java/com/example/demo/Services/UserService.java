package com.example.demo.Services;

import com.example.demo.Entities.User;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.RequestEntities.RequestCreateUser;
import com.example.demo.RequestEntities.RequestUpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User CreateUser(RequestCreateUser requestCreateUser) {
        try {
            User user = new User();
            user.setFirstName(requestCreateUser.getFirstName());
            user.setUsername(requestCreateUser.getUsername());
            user.setLastName(requestCreateUser.getLastName());
            user.setEmail(requestCreateUser.getEmail());
            user.setPassword(requestCreateUser.getPassword());
            user.setRole(requestCreateUser.getRole());
            user.setIsDeleted(false);
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
    }

    public User deleteUserById(String id) {
        try{
            User user = getUserById(id);
            user.setIsDeleted(true);
            return userRepository.save(user);
    }catch (Exception e){
            throw new RuntimeException(e.getMessage());}
    }

    public User updateUser(String id, RequestUpdateUser requestUpdateUser) {
        try {
            User user = getUserById(id);
            user.setFirstName(requestUpdateUser.getFirstName());
            user.setLastName(requestUpdateUser.getLastName());
            user.setPassword(requestUpdateUser.getPassword());
            user.setUsername(requestUpdateUser.getUsername());
            user.setRole(requestUpdateUser.getRole());
            user.setIsDeleted(false);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
