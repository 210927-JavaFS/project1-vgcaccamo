package com.revature.services;

import com.revature.models.User;
import com.revature.models.LoginDTO;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;

public class LoginService {

    private UserDAO userDAO = new UserDAOImpl();

    public boolean login(LoginDTO loginDTO) {
        User user = userDAO.findByUsername(loginDTO.getUsername());
        if (user != null && loginDTO.getPassword().hashCode() == user.getPassword()) {
            return true;
        }
        return false;
    }
}
