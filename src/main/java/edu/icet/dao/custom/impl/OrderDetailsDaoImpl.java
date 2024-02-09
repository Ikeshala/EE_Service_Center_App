package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.OrderDetailsDao;
import edu.icet.dto.OrderDetailsDto;
import edu.icet.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException {
        return false;
    }
}
