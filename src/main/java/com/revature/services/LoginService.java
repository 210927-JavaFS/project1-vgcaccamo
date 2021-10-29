package com.revature.services;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;

public class LoginService {

    private UserDAO userDAO = new UserDAOImpl();

    public boolean login(UserDTO userDTO) {
        User user = userDAO.findByUsername(userDTO.getUsername());
        if (user != null && userDTO.getPassword().hashCode() == user.getPassword()) {
            return true;
        }
        return false;
    }
}
