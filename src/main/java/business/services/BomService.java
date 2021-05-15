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
        billOfMaterials.add(calculateVandbrædtFrontFromMeasurements(width, length));

        //Add Tagplader
        billOfMaterials.add(calculateTagpladerFromMeasurements(width, length));

        //Add Tilbehør
        billOfMaterials.add(calculateBundskruerFromMeasurements(width, length));
        billOfMaterials.add(calculateHulbåndFromMeasurements(width, length));
        billOfMaterials.add(calculateUniversalskruerHøjreFromMeasurements(width, length));
        billOfMaterials.add(calculateUniversalskruerVenstreFromMeasurements(width, length));
        billOfMaterials.add(calculateSternAndVandbrætSkruerFromMeasurements(width, length));
        billOfMaterials.add(calculateBeslagskruerFromMeasurements(width, length));
        billOfMaterials.add(calculateBræddeboltFromMeasurements(width, length));
        billOfMaterials.add(calculateFirkantskiverFromMeasurements(width, length));
        billOfMaterials.add(calculateSkruerYdersteBeklædningFromMeasurements(width, length));
        billOfMaterials.add(calculateSkruerIndersteBeklædningFromMeasurements(width, length));

        //Skur Tilbehør
        //billOfMaterials.add(calculateStalddørsgrebFromMeasurements(width, length));
        //billOfMaterials.add(calculateThængselFromMeasurements(width, length));
        //billOfMaterials.add(calculateVinkelbeslagFromMeasurements(width, length));

        return billOfMaterials;
    }

    public BomLine calculateStolperFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(6);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        if (length > 510) {
            quantity = 6;
        }
        int materialLength = 300;
        String unit = material.getUnit();
        String description = "Stolper nedgraves 90 cm. i jord";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateSpærFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(5);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = length / 55 + 1;
        int materialLength = width;
        String unit = material.getUnit();
        String description = "Spær, monteres på rem";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }


        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateRemFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(5);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 2;
        int materialLength = length;
        String unit = material.getUnit();
        String description = "Remme i sider, sadles ned i stolper";
        double price = 0;

        if (length > 600) {
            quantity = 3;
        }

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateUndersternOneFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(1);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        int materialLength = 360;
        String unit = material.getUnit();
        String description = "understernbrædder til for & bag ende";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateUndersternTwoFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(1);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        int materialLength = 540;
        String unit = material.getUnit();
        String description = "understernbrædder til siderne";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateOversternOneFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(2);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 2;
        int materialLength = 360;
        String unit = material.getUnit();
        String description = "oversternbrædder til forenden";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateOversternTwoFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(2);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        int materialLength = 540;
        String unit = material.getUnit();
        String description = "oversternbrædder til siderne";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateVandbrædtSidesFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(7);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 4;
        int materialLength = 540;
        String unit = material.getUnit();
        String description = "vandbrædt på stern i sider";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateVandbrædtFrontFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(7);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 2;
        int materialLength = 360;
        String unit = material.getUnit();
        String description = "vandbrædt på stern i forende";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateTagpladerFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(8);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        double decimalLength = (double) length;
        double x = decimalLength / 130;
        int quantity = (int) Math.ceil(x);
        int materialLength = width;
        String unit = material.getUnit();
        String description = "tagplader monteres på spær";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, materialLength, unit, description, price);

        return bomLine;
    }

    public BomLine calculateBundskruerFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(9);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 3;
        String unit = material.getUnit();
        String description = "Skruer til tagplader";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateHulbåndFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(10);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        double diagonalLength = Math.sqrt((width * width) + (length * length));
        int quantity = 1;
        if (diagonalLength * 2 > 1000)
            quantity = 2;
        String unit = material.getUnit();
        String description = "Til vindkryds på spær";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price, diagonalLength);

        return bomLine;
    }

    public BomLine calculateUniversalskruerHøjreFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(11);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = calculateSpærFromMeasurements(width, length).getQuantity();
        String unit = material.getUnit();
        String description = "Til montering af spær på rem";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateUniversalskruerVenstreFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(12);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = calculateSpærFromMeasurements(width, length).getQuantity();
        String unit = material.getUnit();
        String description = "Til montering af spær på rem";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateSternAndVandbrætSkruerFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(13);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 1;
        String unit = material.getUnit();
        String description = "Til montering af stern & vandbrædt";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateBeslagskruerFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(14);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 3;
        String unit = material.getUnit();
        String description = "Til montering af universalbeslag & hulbånd";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateBræddeboltFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(15);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 18;
        String unit = material.getUnit();
        String description = "Til montering af rem på stolper";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateFirkantskiverFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(16);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 12;
        String unit = material.getUnit();
        String description = "Til montering af rem på stolper";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateSkruerYdersteBeklædningFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(17);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 2;
        String unit = material.getUnit();
        String description = "Til montering af yderste beklædning";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateSkruerIndersteBeklædningFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(18);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 2;
        String unit = material.getUnit();
        String description = "Til montering af yderste beklædning";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateStalddørsgrebFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(19);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 1;
        String unit = material.getUnit();
        String description = "Til lås på dør i skur";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateThængselFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(20);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 2;
        String unit = material.getUnit();
        String description = "Til skurdør";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }

    public BomLine calculateVinkelbeslagFromMeasurements(int width, int length) throws UserException {
        Material material = materialFacade.getMaterialById(21);
        int materialId = material.getMaterialId();
        String name = material.getDescription();
        int quantity = 32;
        String unit = material.getUnit();
        String description = "Til montering af løsholter i skur";
        double price = 0;

        for (int i = 0; i < quantity; i++) {
            price += material.getPricePerUnit();
        }

        BomLine bomLine = new BomLine(materialId, name, quantity, unit, description, price);

        return bomLine;
    }
}