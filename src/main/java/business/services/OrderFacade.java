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

    public void updateOrderStatus(int orderId) throws UserException {
        orderMapper.updateOrderStatus(orderId);
    }
    public List<Order> getAllOrders() throws UserException {
      return orderMapper.getAllOrders();
    }

}
