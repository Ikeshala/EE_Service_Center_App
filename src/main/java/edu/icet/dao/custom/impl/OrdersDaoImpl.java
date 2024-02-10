package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.OrdersDao;
import edu.icet.dao.util.HibernateUtil;
import edu.icet.dto.OrdersDto;
import edu.icet.entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        Date currentDate = new Date();
        entity.setOrderDate(currentDate);
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Orders order = session.get(Orders.class, value);
        session.delete(order);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        List<Orders> orders = session.createQuery("FROM Orders", Orders.class).list();
        session.close();
        return orders;
    }

    @Override
    public OrdersDto getLastOrder() throws SQLException, ClassNotFoundException {
        return null;
    }
}
