package com.revature.repos;

import com.revature.models.ReimbursementType;
import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReimbursementTypeDAOImpl implements ReimbursementTypeDAO {

    private static Logger log = LoggerFactory.getLogger(ReimbursementTypeDAOImpl.class);

    @Override
    public ReimbursementType findById(int id) {
        log.info("finding reimbursement type by id (DAO)");
        Session session = HibernateUtil.getSession();
        return session.get(ReimbursementType.class, id);
    }

    @Override
    public boolean addType(ReimbursementType rType) {
        log.info("adding reimbursement type (DAO)");
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(rType);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateType(ReimbursementType rType) {
        log.info("updating reimbursement type (DAO)");
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(rType);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteType(ReimbursementType rType) {
        log.info("deleting reimbursement type (DAO)");
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(rType);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
