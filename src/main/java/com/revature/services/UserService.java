package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import com.revature.repos.UserRoleDAO;
import com.revature.repos.UserRoleDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService {

    private UserDAO userDAO = new UserDAOImpl();
    private UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
    private static Logger log = LoggerFactory.getLogger(UserService.class);

    public boolean addUser(User user) {
        log.info("adding user (service)");
        return userDAO.addUser(user);
    }

    public User findById(int id) {
        log.info("finding user by id (service)");
        return userDAO.findById(id);
    }

    public UserRole getRole(int id) {
        log.info("getting user role (service)");
        return userRoleDAO.findById(id);
    }

    public List<User> getAll() {
        log.info("getting all users (service)");
        return userDAO.findAll();
    }
}
