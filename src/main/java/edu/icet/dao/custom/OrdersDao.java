package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.dto.OrdersDto;
import edu.icet.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDao extends CrudDao<Orders> {
    boolean saveOrderDetails(List<OrdersDto> list) throws SQLException, ClassNotFoundException;
}
