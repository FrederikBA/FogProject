package business.services;

import business.entities.BomLine;
import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;

import java.util.List;

public class BomService {
    MaterialFacade materialFacade;


    public BomService(Database database) {
        this.materialFacade = new MaterialFacade(database);
    }


    public BomLine calculatePostsFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(1);
        int materialId = material.getMaterialId();
        String name = material.getName();
        int quantity = 4;
        int materialLength = 500;
        String description = material.getDescription();
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine tmpBomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return tmpBomLine;
    }

    public BomLine calculateRaftersFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(2);
        int materialId = material.getMaterialId();
        String name = material.getName();
        int quantity = 12;
        int materialLength = 500;
        String description = material.getDescription();
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine tmpBomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return tmpBomLine;
    }
}
