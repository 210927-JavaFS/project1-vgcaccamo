package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import com.revature.repos.UserRoleDAO;
import com.revature.repos.UserRoleDAOImpl;

import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAOImpl();
    private UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    public User findById(int id) {
        return userDAO.findById(id);
    }

    public UserRole getRole(int id) {
        return userRoleDAO.findById(id);
    }

    public List<User> getAll() {
        return userDAO.findAll();
    }
}
