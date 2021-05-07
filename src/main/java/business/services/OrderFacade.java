package business.services;

import business.entities.BomLine;
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
}
