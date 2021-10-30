package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;
import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;
import com.revature.repos.ReimbursementStatusDAO;
import com.revature.repos.ReimbursementStatusDAOImpl;

import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
    private ReimbursementStatusDAO reimbursementStatusDAO= new ReimbursementStatusDAOImpl();

    public List<Reimbursement> getAll() {
        return reimbursementDAO.findAll();
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

}
