package com.revature.controllers;

import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.LoginService;
import com.revature.services.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class UserController implements Controller {

    private LoginService loginService = new LoginService();
    private UserService userService = new UserService();

    private Handler loginAttempt = ctx -> {
        LoginDTO loginDTO = ctx.bodyAsClass(LoginDTO.class);
        if (loginService.login(loginDTO)) {
            ctx.req.getSession();
            ctx.status(200);
        } else {
            ctx.req.getSession().invalidate();
            ctx.status(401);
        }
    };

    private Handler RegisterUser = ctx -> {
        if (ctx.req.getSession(false) != null) {
            UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
            User user = new User(userDTO.getUsername(), userDTO.getPassword().hashCode(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userService.getRole(1));
            if (userService.addUser(user)) {
                ctx.status(201);
            } else {
                ctx.status(400);
            }
        } else {
            ctx.status(401);
        }
    };

    private Handler getAllUsers = ctx -> {
        if (ctx.req.getSession(false) != null) {
            List<User> list = userService.getAll();
            ctx.json(list);
            ctx.status(200);
        } else {
            ctx.status(401);
        }
    };

    /* used for creating initial roles
    private Handler addRole = ctx -> {
        UserRole userRole = ctx.bodyAsClass(UserRole.class);
        if (userService.addRole(userRole)) {
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    };
     */

    @Override
    public void addRoutes(Javalin app) {
        app.post("/login", this.loginAttempt);
        app.post("/register", this.RegisterUser);
        app.get("/users", this.getAllUsers);
    }
}
