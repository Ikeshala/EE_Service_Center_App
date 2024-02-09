package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.CustomerDao;
import edu.icet.dao.util.HibernateUtil;
import edu.icet.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class, entity.getCustomerId());
        customer.setCustomerName(entity.getCustomerName());
        customer.setCustomerEmail(entity.getCustomerEmail());
        session.save(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Customer.class,value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query<Customer> query = session.createQuery("FROM " + Customer.class.getSimpleName(), Customer.class);
        List<Customer> list = query.list();
        session.close();
        return list;
    }

}
