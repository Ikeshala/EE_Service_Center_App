package edu.icet.dao;

import edu.icet.dao.custom.impl.CustomerDaoImpl;
import edu.icet.dao.custom.impl.ItemDaoImpl;
import edu.icet.dao.custom.impl.OrdersDaoImpl;
import edu.icet.dao.custom.impl.UserDaoImpl;
import edu.icet.dao.util.DaoType;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return daoFactory != null? daoFactory:(daoFactory = new DaoFactory());
    }
    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case USER: return(T) new UserDaoImpl();
            case CUSTOMER: return(T) new CustomerDaoImpl();
            case ITEM: return(T) new ItemDaoImpl();
            case ORDERS: return(T) new OrdersDaoImpl();
        }
        return null;
    }
}
