package com.revature.repos;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO {

    private static Logger log = LoggerFactory.getLogger(ReimbursementStatusDAOImpl.class);

    @Override
    public ReimbursementStatus findById(int id) {
        log.info("finding reimbursement status by id (DAO)");
        Session session = HibernateUtil.getSession();
        return session.get(ReimbursementStatus.class, id);
    }

    @Override
    public boolean addStatus(ReimbursementStatus rStatus) {
        log.info("adding reimbursement status (DAO)");
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
        log.info("updating reimbursement status (DAO)");
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
        log.info("deleting reimbursement status (DAO)");
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
