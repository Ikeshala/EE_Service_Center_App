package edu.icet.dao.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateUtil {
    private static SessionFactory sessionFactory = createSessionFactory();
    private static SessionFactory createSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }
    public static Session getSession(){
        return sessionFactory.openSession();
    }

}
