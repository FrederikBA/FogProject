package business.services;

import business.entities.BomLine;
import business.exceptions.UserException;
import business.persistence.MaterialMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import business.persistence.Database;
import web.commands.Command;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static web.FrontController.database;

class BomServiceTest {
    private final static String USER = "root";
    private final static String PASSWORD = "root123";
    private final static String URL = "jdbc:mysql://localhost:3306/fog_carport?serverTimezone=CET";

    private static Database database;

    private static BomService bomService;

    @BeforeEach
    void setUp() {
        try {
            database = new Database(USER, PASSWORD, URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        bomService = new BomService(database);

    }

    @Test
    void calculateStolperFromMeasurements() throws UserException {

        BomLine result = bomService.calculateStolperFromMeasurements(600, 780);
        assertEquals(6, result.getQuantity());
        assertEquals(300, result.getLength());
    }

    @Test
    void calculateSpærFromMeasurements() throws UserException {
        BomLine spær = bomService.calculateSpærFromMeasurements(600, 780);
        assertEquals(14, spær.getQuantity());
        assertEquals(1400, spær.getPrice());
    }

    @Test
    void calculateRemFromMeasurements() {
    }

    @Test
    void calculateHulbåndFromMeasurements() throws UserException {
        BomLine hulbånd = bomService.calculateHulbåndFromMeasurements(600, 780);
        assertEquals(2, hulbånd.getQuantity());
        assertEquals(100, hulbånd.getPrice());
    }
}