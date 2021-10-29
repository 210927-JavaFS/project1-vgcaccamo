package com.revature.repos;

import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO {
    @Override
    public List<Reimbursement> findAll() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("FROM Reimbursement").list();
    }

    @Override
    public Reimbursement findById(int id) {
        Session session = HibernateUtil.getSession();
        return session.get(Reimbursement.class, id);
    }

    @Override
    public boolean addReimbursement(Reimbursement reimbursement) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(reimbursement);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateReimbursement(Reimbursement reimbursement) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(reimbursement);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteReimbursement(Reimbursement reimbursement) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.delete(reimbursement);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}