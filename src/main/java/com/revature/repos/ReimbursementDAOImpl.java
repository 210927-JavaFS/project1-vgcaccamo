package com.revature.repos;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO {

    private static Logger log = LoggerFactory.getLogger(ReimbursementDAOImpl.class);

    @Override
    public List<Reimbursement> findAll() {
        log.info("finding all reimbursements (DAO)");
        Session session = HibernateUtil.getSession();
        return session.createQuery("FROM Reimbursement").list();
    }

    @Override
    public Reimbursement findById(int id) {
        log.info("finding reimbursement by id (DAO)");
        Session session = HibernateUtil.getSession();
        return session.get(Reimbursement.class, id);
    }

    @Override
    public List<Reimbursement> findByAuthor(User author) {
        log.info("finding reimbursement by author (DAO)");
        Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Reimbursement> cr = cb.createQuery(Reimbursement.class);
        Root<Reimbursement> root = cr.from(Reimbursement.class);
        cr.select(root).where(root.get("author").in(author));

        Query<Reimbursement> query = session.createQuery(cr);
        List<Reimbursement> result = query.getResultList();
        return result;
    }

    @Override
    public List<Reimbursement> findByStatus(ReimbursementStatus status) {
        log.info("finding reimbursement by status (DAO)");
        Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Reimbursement> cr = cb.createQuery(Reimbursement.class);
        Root<Reimbursement> root = cr.from(Reimbursement.class);
        cr.select(root).where(root.get("status").in(status));

        Query<Reimbursement> query = session.createQuery(cr);
        List<Reimbursement> result = query.getResultList();
        return result;
    }

    @Override
    public boolean addReimbursement(Reimbursement reimbursement) {
        log.info("adding reimbursement (DAO)");
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
        log.info("updating reimbursement (DAO)");
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
        log.info("deleting reimbursement (DAO)");
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
