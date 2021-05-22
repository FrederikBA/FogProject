package business.services;

import business.entities.BomLine;
import business.exceptions.UserException;
import business.persistence.MaterialMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import business.persistence.Database;
import web.commands.Command;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static web.FrontController.database;

class BomServiceTest {
    private final static String USER = "root";
    private final static String PASSWORD = "root";
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
        //Ækvivalensklassepartionering, Tjekke hvor går grænsen fra at have 4 eller 6 stolper.
        //Test 1
        BomLine result = bomService.calculateStolperFromMeasurements(600, 631);
        assertNotNull(result);
        assertEquals(6, result.getQuantity());
        assertEquals(300, result.getLength());
        //Test 2
        BomLine result1 = bomService.calculateStolperFromMeasurements(600, 630);
        assertNotNull(result1);
        assertEquals(4, result1.getQuantity());


    }

    @Test
    void calculateSpærFromMeasurements() throws UserException {
        //Case One
        BomLine resultOne = bomService.calculateSpærFromMeasurements(600, 780);
        assertNotNull(resultOne);
        assertEquals(14, resultOne.getQuantity());
        assertEquals(600, resultOne.getLength());
        assertEquals(1400, resultOne.getPrice());

        //Case Two
        BomLine resultTwo = bomService.calculateSpærFromMeasurements(300, 450);
        assertNotNull(resultTwo);
        assertEquals(9, resultTwo.getQuantity());
        assertEquals(300, resultTwo.getLength());
        assertEquals(900, resultTwo.getPrice());
    }

    @Test
    void calculateRemFromMeasurements() throws UserException {
        //Ækvivalensklassepartionering, Tjekke hvor går grænsen fra at have 2 eller 3 remme.
        //Test 1
        BomLine result = bomService.calculateRemFromMeasurements(3,601);
        assertNotNull(result);
        assertEquals(3,result.getQuantity());
        //Test 2
        BomLine result1 = bomService.calculateRemFromMeasurements(3,600);
        assertNotNull(result1);
        assertEquals(2,result1.getQuantity());


    }

    @Test
    void calculateHulbåndFromMeasurements() throws UserException {
        BomLine result = bomService.calculateHulbåndFromMeasurements(600, 780);
        assertNotNull(result);
        assertEquals(2, result.getQuantity());
        assertEquals(100, result.getPrice());

        //Case Two
        BomLine resultTwo = bomService.calculateHulbåndFromMeasurements(300, 300);
        assertNotNull(resultTwo);
        assertEquals(1, resultTwo.getQuantity());
        assertEquals(50, resultTwo.getPrice());
    }
}