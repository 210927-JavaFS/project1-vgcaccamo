package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReimbursementController implements Controller {

    ReimbursementService reimbursementService = new ReimbursementService();
    UserService userService = new UserService();
    private static Logger log = LoggerFactory.getLogger(ReimbursementController.class);

    private Handler viewAllReimbursements = ctx -> {
        log.info("getting all reimbursements (controller)");
        if (ctx.req.getSession(false) != null) {
            List<Reimbursement> list = reimbursementService.getAll();
            ctx.json(list);
            ctx.status(200);
        } else {
            ctx.status(401);
        }
    };

    private Handler viewReimbursementsByAuthor = ctx -> {
        log.info("viewing reimbursements by author");
        if (ctx.req.getSession(false) != null) {
            User author = userService.findById(Integer.parseInt(ctx.pathParam("authorId")));
            List<Reimbursement> list = reimbursementService.getByAuthor(author);
            ctx.json(list);
            ctx.status(200);
        } else {
            ctx.status(401);
        }
    };

    private Handler viewReimbursementsByStatus = ctx -> {
        log.info("viewing reimbursements by status (controller)");
        if (ctx.req.getSession(false) != null) {
            ReimbursementStatus status = reimbursementService.getStatusById(Integer.parseInt(ctx.pathParam("statusId")));
            List<Reimbursement> list = reimbursementService.getByStatus(status);
            ctx.json(list);
            ctx.status(200);
        } else {
            ctx.status(401);
        }
    };

    private Handler addReimbursement = ctx -> {
        log.info("adding a reimbursement (controller)");
        if (ctx.req.getSession(false) != null) {
            Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
            Reimbursement newReimbursement = new Reimbursement(reimbursement.getAmount(), reimbursement.getDescription(), reimbursement.getSubmitted(), reimbursement.getAuthor(), reimbursementService.getStatusById(1), reimbursement.getType());
            if (reimbursementService.addReimbursement(newReimbursement)) {
                ctx.status(201);
            } else {
                ctx.status(400);
            }
        } else {
            ctx.status(401);
        }
    };

    private Handler getReimbursementById = ctx -> {
        log.info("getting a reimbursement by id (controller)");
        if (ctx.req.getSession(false) != null) {
            Reimbursement reimbursement = reimbursementService.getById(Integer.parseInt(ctx.pathParam("id")));
            ctx.json(reimbursement);
            ctx.status(200);
        } else {
            ctx.status(401);
        }
    };

    private Handler updateReimbursement = ctx -> {
        log.info("updating a reimbursement (controller)");
        if (ctx.req.getSession(false) != null) {
            Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
            if (reimbursementService.updateReimbursement(reimbursement)) {
                ctx.status(201);
            } else {
                ctx.status(400);
            }
        } else {
            ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/reimbursements", this.viewAllReimbursements);
        app.get("/reimbursements/author/:authorId", this.viewReimbursementsByAuthor);
        app.get("/reimbursements/status/:statusId", this.viewReimbursementsByStatus);
        app.post("/reimbursements", this.addReimbursement);
        app.get("/reimbursements/:id", this.getReimbursementById);
        app.put("/reimbursements/:id", this.updateReimbursement);
    }
}
