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

        //Add Stolper
        billOfMaterials.add(calculateStolperFromMeasurements(width, length));

        //Add Spær
        billOfMaterials.add(calculateSpærFromMeasurements(width, length));

        //Add Rem
        billOfMaterials.add(calculateRemFromMeasurements(width, length));

        //Add Sternbrædder
        billOfMaterials.add(calculateUndersternOneFromMeasurements(width, length));
        billOfMaterials.add(calculateUndersternTwoFromMeasurements(width, length));
        billOfMaterials.add(calculateOversternOneFromMeasurements(width, length));
        billOfMaterials.add(calculateOversternTwoFromMeasurements(width, length));

        //Add Vandbrædt
        billOfMaterials.add(calculateVandbrædtSidesFromMeasurements(width, length));
        billOfMaterials.add(calculateVandBrædtFrontFromMeasurements(width, length));

        //Add Tagplader
        billOfMaterials.add(calculateTagpladerFromMeasurements(width, length));

        return billOfMaterials;
    }

    public BomLine calculateStolperFromMeasurements(int width, int length) throws UserException {
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

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }

    public BomLine calculateSpærFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(5);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = length / 55 + 1;
        int materialLength = width;
        String description = "Spær, monteres på rem";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }


        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }

    public BomLine calculateRemFromMeasurements(int width, int length) throws UserException {
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

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }

    public BomLine calculateUndersternOneFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(1);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        int materialLength = 360;
        String description = "understernbrædder til for & bag ende";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }

    public BomLine calculateUndersternTwoFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(1);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        int materialLength = 540;
        String description = "understernbrædder til siderne";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price = material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }

    public BomLine calculateOversternOneFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(2);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 2;
        int materialLength = 360;
        String description = "oversternbrædder til forenden";
        double price = 0;
        for (int i = 0; i < quantity; i++) {
            price = material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }

    public BomLine calculateOversternTwoFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(2);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        int materialLength = 540;
        String description = "oversternbrædder til siderne";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price = material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }

    public BomLine calculateVandbrædtSidesFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(7);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        int materialLength = 540;
        String description = "vandbrædt på stern i sider";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price = material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }

    public BomLine calculateVandBrædtFrontFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(7);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 2;
        int materialLength = 360;
        String description = "vandbrædt på stern i forende";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price = material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }

    public BomLine calculateTagpladerFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(8);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        double decimalLength = (double)length;
        double x = decimalLength / 130;
        int quantity = (int) Math.ceil(x);
        int materialLength = width;
        String description = "tagplader monteres på spær";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price = material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, description, price);

        return bomLine;
    }
}
