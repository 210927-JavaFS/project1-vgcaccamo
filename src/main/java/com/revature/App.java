package com.revature;

import com.revature.controllers.Controller;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static Javalin app;
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("starting app");
        app = Javalin.create(config ->
                config.addStaticFiles("/static", Location.CLASSPATH));

        configure(new UserController(), new ReimbursementController());
        app.start(8081);
        log.info("app started");
    }

    private static void configure(Controller... controllers) {
        log.info("configuring controllers");
        for (Controller c : controllers) {
            c.addRoutes(app);
        }
    }
}
