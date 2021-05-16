package business.services;

import business.entities.BomLine;
import business.exceptions.UserException;
import business.persistence.BomMapper;
import business.persistence.Database;

import java.util.List;

public class BomFacade {
    BomMapper bomMapper;

    public BomFacade(Database database) {
        bomMapper = new BomMapper(database);
    }

    public List<BomLine> getBomByOrderId(int orderId) throws UserException {
        return bomMapper.getBomByOrderId(orderId);
    }

    public BomLine getBomLineByFromOrderByMaterialId(int orderId, int materialId) throws UserException {
        return bomMapper.getBomLineByFromOrderByMaterialId(orderId, materialId);
    }
}
