package business.services;

import business.entities.BomLine;
import business.entities.Material;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;

import java.util.ArrayList;
import java.util.List;

public class BomService {
    MaterialFacade materialFacade;


    public BomService(Database database) {
        this.materialFacade = new MaterialFacade(database);
    }

    public List<BomLine> calculateCarportFromMeasurements(int width, int length) throws UserException {
        List<BomLine> billOfMaterials = new ArrayList<>();
        //Add Posts
        billOfMaterials.add(calculatePostsFromMeasurements(width, length));
        //Add Rafters
        billOfMaterials.add(calculateRaftersFromMeasurements(width, length));
        //Add Wall Plates
        billOfMaterials.add(calculateWallPlateFromMeasurements(width, length));

        return billOfMaterials;
    }

    public BomLine calculatePostsFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(6);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        if (length >= 650) {
            quantity = 6;
        }
        int materialLength = 300;
        String description = "Stolper nedgraves 90 cm. i jord";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine tmpBomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return tmpBomLine;
    }

    public BomLine calculateRaftersFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(5);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = length / 50;
        int materialLength = width;
        String description = "Spær, monteres på rem";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine tmpBomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return tmpBomLine;
    }

    public BomLine calculateWallPlateFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(5);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 2;
        int materialLength = length;
        String description = "Remme i sider, sadles ned i stolper";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine tmpBomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return tmpBomLine;
    }

}
