package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.repos.*;

import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
    private ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();
    private ReimbursementTypeDAO reimbursementTypeDAO = new ReimbursementTypeDAOImpl();

    public List<Reimbursement> getAll() {
        return reimbursementDAO.findAll();
    }

    public Reimbursement getById(int id) {
        return reimbursementDAO.findById(id);
    }

    public List<Reimbursement> getByAuthor(User author) {
        return reimbursementDAO.findByAuthor(author);
    }

    public List<Reimbursement> getByStatus(ReimbursementStatus status) {
        return reimbursementDAO.findByStatus(status);
    }

    public boolean addReimbursement(Reimbursement reimbursement) {
        return reimbursementDAO.addReimbursement(reimbursement);
    }

    public boolean updateReimbursement(Reimbursement reimbursement) {
        return reimbursementDAO.updateReimbursement(reimbursement);
    }

    public ReimbursementStatus getStatusById(int id) {
        return reimbursementStatusDAO.findById(id);
    }

    public ReimbursementType getTypeById(int id) {
        return reimbursementTypeDAO.findById(id);
    }

}
