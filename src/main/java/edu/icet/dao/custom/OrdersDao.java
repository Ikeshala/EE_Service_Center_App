package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.dto.OrdersDto;
import edu.icet.entity.Orders;

import java.sql.SQLException;

public interface OrdersDao extends CrudDao<Orders> {
    OrdersDto getLastOrder() throws SQLException, ClassNotFoundException;
}
