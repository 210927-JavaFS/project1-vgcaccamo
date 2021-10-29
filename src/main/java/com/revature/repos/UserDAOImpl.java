package com.revature.repos;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public List<User> findAll() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("FROM User").list();
    }

    @Override
    public User findById(int id) {
        Session session = HibernateUtil.getSession();
        return session.get(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        Session session = HibernateUtil.getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(root.get("username").in(username));

        Query<User> query = session.createQuery(cr);
        User result = query.getSingleResult();
        return result;
    }

    @Override
    public boolean addUser(User user) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
