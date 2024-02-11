package edu.icet.bo.custom.impl;

import edu.icet.bo.custom.OrdersBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.CustomerDao;
import edu.icet.dao.custom.ItemDao;
import edu.icet.dao.custom.OrdersDao;
import edu.icet.dao.util.DaoType;
import edu.icet.dto.OrdersDto;
import edu.icet.entity.Orders;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersBoImpl implements OrdersBo {
    private OrdersDao ordersDao = DaoFactory.getInstance().getDao(DaoType.ORDERS);
    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    private ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);

    @Override
    public boolean saveOrders(OrdersDto dto) throws SQLException, ClassNotFoundException {
        Orders orders = new Orders();
        orders.setOrderId(dto.getOrderId());
        orders.setOrderDate(dto.getOrderDate());
        orders.setCustomerId(customerDao.getById(dto.getCustomerId()));
        orders.setItemCode(itemDao.getByCode(dto.getItemCode()));
        orders.setRepair(dto.getRepair());
        orders.setStatus(dto.getStatus());
        return ordersDao.save(orders);
    }

    @Override
    public boolean updateOrders(OrdersDto dto) throws SQLException, ClassNotFoundException {
        Orders existingOrder = ordersDao.getAll().stream()
                .filter(order -> order.getOrderId().equals(dto.getOrderId()))
                .findFirst()
                .orElse(null);
        if (existingOrder == null) {
            return false;
        }

        existingOrder.setCustomerId(customerDao.getById(dto.getCustomerId()));
        existingOrder.setItemCode(itemDao.getByCode(dto.getItemCode()));
        existingOrder.setRepair(dto.getRepair());
        existingOrder.setStatus(dto.getStatus());

        return ordersDao.update(existingOrder);
    }

    @Override
    public boolean deleteOrders(String id) throws SQLException, ClassNotFoundException {
        return ordersDao.delete(id);
    }

    @Override
    public boolean searchOrders(OrdersDto dto) {
        return false;
    }

    @Override
    public List<OrdersDto> allOrders() throws SQLException, ClassNotFoundException {
        List<Orders> ordersList = ordersDao.getAll();
        List<OrdersDto> ordersDtoList = new ArrayList<>();

        for (Orders order : ordersList) {
            OrdersDto dto = new OrdersDto();
            dto.setOrderId(order.getOrderId());
            dto.setCustomerId(order.getCustomerId().getCustomerId());
            dto.setItemCode(order.getItemCode().getItemCode());
            dto.setItemName(order.getItemCode().getItemName());
            dto.setRepair(order.getRepair());
            dto.setStatus(order.getStatus());
            ordersDtoList.add(dto);
        }

        return ordersDtoList;
    }

    @Override
    public boolean saveOrders(List<OrdersDto> list) {
        try {
            for (OrdersDto dto : list) {
                Orders orders = new Orders();
                orders.setOrderId(dto.getOrderId());
                orders.setCustomerId(customerDao.getById(dto.getCustomerId()));
                orders.setItemCode(itemDao.getByCode(dto.getItemCode()));
                orders.setRepair(dto.getRepair());
                orders.setStatus(dto.getStatus());
                ordersDao.save(orders);
            }
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        String prefix = "EE";
        int sequenceNumber = ordersDao.getAll().size() + 1;
        String generatedId = prefix + String.format("%05d", sequenceNumber);
        return generatedId;
    }
}
