package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.repos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
    private ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();
    private ReimbursementTypeDAO reimbursementTypeDAO = new ReimbursementTypeDAOImpl();
    private static Logger log = LoggerFactory.getLogger(ReimbursementService.class);

    public List<Reimbursement> getAll() {
        log.info("getting all reimbursements (service)");
        return reimbursementDAO.findAll();
    }

    public Reimbursement getById(int id) {
        log.info("getting reimbursement by id (service)");
        return reimbursementDAO.findById(id);
    }

    public List<Reimbursement> getByAuthor(User author) {
        log.info("getting reimbursement by author (service)");
        return reimbursementDAO.findByAuthor(author);
    }

    public List<Reimbursement> getByStatus(ReimbursementStatus status) {
        log.info("getting reimbursement by status (service)");
        return reimbursementDAO.findByStatus(status);
    }

    public boolean addReimbursement(Reimbursement reimbursement) {
        log.info("adding reimbursement (service)");
        return reimbursementDAO.addReimbursement(reimbursement);
    }

    public boolean updateReimbursement(Reimbursement reimbursement) {
        log.info("updating reimbursement by author (service)");
        return reimbursementDAO.updateReimbursement(reimbursement);
    }

    public ReimbursementStatus getStatusById(int id) {
        log.info("getting reimbursement status by id (service)");
        return reimbursementStatusDAO.findById(id);
    }

    public ReimbursementType getTypeById(int id) {
        log.info("getting reimbursement type by id (service)");
        return reimbursementTypeDAO.findById(id);
    }

}
