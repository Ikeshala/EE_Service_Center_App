package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;

import java.sql.SQLException;

public interface OrdersBo extends SuperBo {
    String generateId() throws SQLException, ClassNotFoundException;
}