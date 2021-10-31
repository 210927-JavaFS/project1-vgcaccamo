package com.revature.services;

import com.revature.models.User;
import com.revature.models.LoginDTO;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;

public class LoginService {

    private UserDAO userDAO = new UserDAOImpl();

    public User login(LoginDTO loginDTO) {
        User user = userDAO.findByUsername(loginDTO.getUsername());
        int password = loginDTO.getPassword().hashCode();
        if (user != null && password == user.getPassword()) {
            return user;
        }
        return null;
    }
}
