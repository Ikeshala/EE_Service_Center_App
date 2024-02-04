package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.UserDao;
import edu.icet.dao.util.HibernateUtil;
import edu.icet.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.find(User.class, entity.getUserId());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setType(entity.getType());
        session.save(user);
        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(User.class,value));
        transaction.commit();
        return true;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM user");
        List<User> list = query.list();
        return list;
    }

    @Override
    public boolean searchUser(String id) {
        return false;
    }
}