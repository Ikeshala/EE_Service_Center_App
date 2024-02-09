package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.dto.OrderDetailsDto;
import edu.icet.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDao extends CrudDao<OrderDetails> {
    boolean saveOrderDetails(List<OrderDetailsDto> list) throws SQLException, ClassNotFoundException;
}
