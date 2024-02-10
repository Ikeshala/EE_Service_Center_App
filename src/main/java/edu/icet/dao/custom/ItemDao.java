package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.entity.Item;

import java.sql.SQLException;

public interface ItemDao extends CrudDao<Item> {
    Item getByCode(String itemCode) throws SQLException, ClassNotFoundException;
}
