package com.revature.repos;

import com.revature.models.ReimbursementStatus;

public interface ReimbursementStatusDAO {
    ReimbursementStatus findById(int id);
    boolean addStatus(ReimbursementStatus rStatus);
    boolean updateStatus(ReimbursementStatus rStatus);
    boolean deleteStatus(ReimbursementStatus rStatus);
}
