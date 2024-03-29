package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.ItemDao;
import edu.icet.dao.util.HibernateUtil;
import edu.icet.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.find(Item.class, entity.getItemCode());
        item.setCategory(entity.getCategory());
        item.setItemName(entity.getItemName());
        item.setDescription(entity.getDescription());
        session.update(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Item.class,value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query<Item> query = session.createQuery("FROM " + Item.class.getSimpleName(), Item.class);
        List<Item> list = query.list();
        session.close();
        return list;
    }

    @Override
    public Item getByCode(String itemCode) throws SQLException, ClassNotFoundException {
        List<Item> items = getAll();
        return items.stream()
                .filter(i -> i.getItemCode().equals(itemCode))
                .findFirst()
                .orElse(null);
    }
}