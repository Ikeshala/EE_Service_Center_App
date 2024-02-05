package edu.icet.bo.custom.impl;

import edu.icet.bo.custom.ItemBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.ItemDao;
import edu.icet.dao.util.DaoType;
import edu.icet.dto.ItemDto;
import edu.icet.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    private ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(
                dto.getCategory(),
                dto.getItemCode(),
                dto.getItemName(),
                dto.getDescription(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(
                dto.getCategory(),
                dto.getItemCode(),
                dto.getItemName(),
                dto.getDescription(),
                dto.getQuantity()
        ));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDao.delete(id);
    }

    @Override
    public boolean searchItem(ItemDto dto) {
        return false;
    }

    @Override
    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<Item> entityList = itemDao.getAll();
        List<ItemDto> list = new ArrayList<>();
        for (Item item:entityList) {
            list.add(new ItemDto(
                    item.getCategory(),
                    item.getItemCode(),
                    item.getItemName(),
                    item.getDescription(),
                    item.getQuantity()
            ));
        }
        return list;
    }
}
