package com.revature;

import com.revature.controllers.Controller;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {

    private static Javalin app;

    public static void main(String[] args) {
        app = Javalin.create(config -> {
            config.addStaticFiles("/static", Location.CLASSPATH);
        });
        configure(new UserController(), new ReimbursementController());
        app.start(8081);
    }

    private static void configure(Controller... controllers) {
        for (Controller c : controllers) {
            c.addRoutes(app);
        }
    }
}
