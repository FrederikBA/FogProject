package business.services;

import business.persistence.Database;
import business.persistence.OrderMapper;

public class OrderFacade {
    OrderMapper orderMapper;

    public OrderFacade(Database database) {
        orderMapper = new OrderMapper(database);
    }
}
