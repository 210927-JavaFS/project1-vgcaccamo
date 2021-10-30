package com.revature.repos;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;

import java.util.List;

public interface ReimbursementDAO {
    List<Reimbursement> findAll();
    Reimbursement findById(int id);
    List<Reimbursement> findByAuthor(User author);
    List<Reimbursement> findByStatus(ReimbursementStatus status);
    boolean addReimbursement(Reimbursement reimbursement);
    boolean updateReimbursement(Reimbursement reimbursement);
    boolean deleteReimbursement(Reimbursement reimbursement);
}
