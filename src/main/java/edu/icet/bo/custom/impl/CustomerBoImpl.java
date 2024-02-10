package edu.icet.bo.custom.impl;

import edu.icet.bo.custom.CustomerBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.CustomerDao;
import edu.icet.dao.util.DaoType;
import edu.icet.dto.CustomerDto;
import edu.icet.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.save(new Customer(
                dto.getCustomerId(),
                dto.getCustomerName(),
                dto.getCustomerEmail()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(
                dto.getCustomerId(),
                dto.getCustomerName(),
                dto.getCustomerEmail()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public boolean searchCustomer(CustomerDto dto) {
        return false;
    }

    @Override
    public List<CustomerDto> allCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> entityList = customerDao.getAll();
        List<CustomerDto> list = new ArrayList<>();
        for (Customer customer:entityList) {
            list.add(new CustomerDto(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerEmail()
            ));
        }
        return list;
    }
}
