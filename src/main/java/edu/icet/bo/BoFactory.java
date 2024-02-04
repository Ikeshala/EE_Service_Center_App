package edu.icet.bo;

import edu.icet.bo.custom.impl.UserBoImpl;
import edu.icet.dao.util.BoType;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){}
    public static BoFactory getInstance(){
        return boFactory != null? boFactory:(boFactory = new BoFactory());
    }
    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case USER:return(T) new UserBoImpl();
//            case ITEM:return(T) new ItemBoImpl();
//            case ORDER_DETAIL:return(T) new OrderDetailBoImpl();
//            case ORDER:return(T) new OrdersBoImpl();
        }
        return null;
    }
}
