package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.OrdersDao;
import edu.icet.dto.OrdersDto;
import edu.icet.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrderDetails(List<OrdersDto> list) throws SQLException, ClassNotFoundException {
        return false;
    }
}
