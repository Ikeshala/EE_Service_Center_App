package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.OrdersDto;

import java.sql.SQLException;
import java.util.List;

public interface OrdersBo extends SuperBo {
    boolean saveOrders(OrdersDto dto) throws SQLException, ClassNotFoundException;
    boolean updateOrders(OrdersDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteOrders(String id) throws SQLException, ClassNotFoundException;
    boolean searchOrders(OrdersDto dto);
    List<OrdersDto> allOrders() throws SQLException, ClassNotFoundException;
    boolean saveOrders(List<OrdersDto> list);
    String generateId() throws SQLException, ClassNotFoundException;
}