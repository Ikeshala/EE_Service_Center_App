package edu.icet.dao.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class hibernateUtil {
    private static SessionFactory sessionFactory = createSessionFactory();
    private static SessionFactory createSessionFactory(){
        return null;
    }
    public static Session getSession(){
        return sessionFactory.openSession();
    }

}
