package com.revature.services;

import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService {

    private UserDAO userDAO = new UserDAOImpl();
    private static Logger log = LoggerFactory.getLogger(LoginService.class);

    public User login(LoginDTO loginDTO) {
        log.info("attempting to login (service)");
        User user = userDAO.findByUsername(loginDTO.getUsername());
        int password = loginDTO.getPassword().hashCode();
        if (user != null && password == user.getPassword()) {
            return user;
        }
        return null;
    }
}
