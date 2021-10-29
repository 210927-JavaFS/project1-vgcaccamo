package com.revature.repos;

import com.revature.models.ReimbursementType;

public interface ReimbursementTypeDAO {
    ReimbursementType findById(int id);
    boolean addType(ReimbursementType rType);
    boolean updateType(ReimbursementType rType);
    boolean deleteType(ReimbursementType rType);
}
