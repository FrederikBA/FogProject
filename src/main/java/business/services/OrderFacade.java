package business.services;

import business.entities.BomLine;
import business.entities.Order;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.OrderMapper;

import java.util.List;

public class OrderFacade {
    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }

    public void createOrder(int userId, int length, int width, List<BomLine> bomLines) throws UserException {
        orderMapper.createOrder(userId, length, width, bomLines);
    }

    public int getOrderIdByTimestamp() throws UserException {
        return orderMapper.getOrderIdByTimestamp();
    }

    public double getOrderPriceByTimestamp() throws UserException {
        return orderMapper.getOrderPriceByTimestamp();
    }

    public void updateOrderStatusToPending(int orderId) throws UserException {
        orderMapper.updateOrderStatusToPending(orderId);
    }

    public void updateOrderStatusToConfirmed(int orderId) throws UserException {
        orderMapper.updateOrderStatusToConfirmed(orderId);
    }

    public List<Order> getAllOrders() throws UserException {
        return orderMapper.getAllOrders();
    }

    public List<Order> getAllOrdersByUserId(int userId) throws UserException {
        return orderMapper.getAllOrdersByUserId(userId);
    }
    public int deleteOrder(int orderId) throws UserException {
    return orderMapper.deleteOrder(orderId);
    }
    public int deleteOrderContent(int orderId) throws UserException {
        return orderMapper.deleteOrderContent(orderId);
    }
    }
