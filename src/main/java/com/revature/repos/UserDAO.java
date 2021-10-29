package com.revature.repos;

import com.revature.models.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(int id);
    User findByUsername(String username);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
}
