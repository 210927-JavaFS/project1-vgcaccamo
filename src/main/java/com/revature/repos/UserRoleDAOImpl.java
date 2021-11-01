package com.revature.repos;

import com.revature.models.UserRole;
import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRoleDAOImpl implements UserRoleDAO {

    private static Logger log = LoggerFactory.getLogger(UserRoleDAOImpl.class);

    @Override
    public UserRole findById(int id) {
        log.info("finding user role by id (DAO)");
        Session session = HibernateUtil.getSession();
        return session.get(UserRole.class, id);
    }

    @Override
    public boolean addRole(UserRole userRole) {
        log.info("adding user role (DAO)");
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(userRole);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRole(UserRole userRole) {
        log.info("updating user role (DAO)");
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.merge(userRole);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRole(UserRole userRole) {
        log.info("deleting user role (DAO)");
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.delete(userRole);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
