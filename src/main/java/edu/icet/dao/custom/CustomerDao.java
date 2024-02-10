package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.dto.OrdersDto;
import edu.icet.entity.Customer;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer> {
    Customer getById(String customerId) throws SQLException, ClassNotFoundException;
}
