package edu.icet.bo.custom;

import edu.icet.bo.SuperBo;
import edu.icet.dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBo {
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    boolean searchItem(ItemDto dto);
    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;
}