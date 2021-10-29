package com.revature.repos;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO {
    @Override
    public ReimbursementStatus findById(int id) {
        Session session = HibernateUtil.getSession();
        return session.get(ReimbursementStatus.class, id);
    }

    @Override
    public boolean addStatus(ReimbursementStatus rStatus) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(rStatus);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStatus(ReimbursementStatus rStatus) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(rStatus);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStatus(ReimbursementStatus rStatus) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.delete(rStatus);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
