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

class BomServiceTest   {
    private final static String DATABASE = "fog_carport";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

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

        BomLine result = bomService.calculateStolperFromMeasurements(780,300);
        assertEquals(6,result.getQuantity());
        assertEquals(300, result.getLength());

    }

    @Test
    void calculateSpærFromMeasurements() {
    }

    @Test
    void calculateRemFromMeasurements() {
    }

    @Test
    void calculateHulbåndFromMeasurements() {
    }
}