package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {
    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean searchCustomer(CustomerDto dto);
    List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException;
}