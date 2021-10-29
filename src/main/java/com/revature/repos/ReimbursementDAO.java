package com.revature.repos;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {
    List<Reimbursement> findAll();
    Reimbursement findById(int id);
    boolean addReimbursement(Reimbursement reimbursement);
    boolean updateReimbursement(Reimbursement reimbursement);
    boolean deleteReimbursement(Reimbursement reimbursement);
}
