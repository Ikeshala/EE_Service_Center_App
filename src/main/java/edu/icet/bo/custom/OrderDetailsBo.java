package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsBo extends SuperBo {
    String generateId() throws SQLException, ClassNotFoundException;
}