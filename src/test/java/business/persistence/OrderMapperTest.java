package business.persistence;

import business.entities.BomLine;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.BomService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    private final static String DATABASE = "fog_carport";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "root123";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static UserMapper userMapper;
    private static MaterialMapper materialMapper;
    private static OrderMapper orderMapper;
    private static BomService bomService;

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            userMapper = new UserMapper(database);
            materialMapper = new MaterialMapper(database);
            bomService = new BomService(database);
            orderMapper = new OrderMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @BeforeEach
    void setUp() {
        // reset test database
        try (Statement stmt = database.connect().createStatement()) {

            stmt.execute("delete from bom_items");
            stmt.execute("delete from orders");
            stmt.execute("delete from user");
            stmt.execute(
                    "insert into user values " +
                            "(1,'jens@somewhere.com','jensen','customer'), " +
                            "(2,'ken@somewhere.com','kensen','customer'), " +
                            "(3,'robin@somewhere.com','batman','employee')");

            stmt.execute(
                    "insert into orders values " +
                            "(1,1, '250','bekræftet','240','240','2021-05-15'), " +
                            "(2,2, '300','bekræftet','140','740','2021-05-14'), " +
                            "(3,3, '750','bekræftet','540','640','2021-05-18')");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    void createOrder() throws UserException {
        Timestamp orderDate = Timestamp.valueOf("2021-05-21 00:00:00");
        Order order = new Order(1, 1, orderDate, 544.5, "bekræftet");
        order.setOrderId(new User(1, "jens@somewhere.com", "jensen", "customer"));
        List<BomLine> bomLines = bomService.calculateCarportFromMeasurements(300, 700);
        orderMapper.createOrder(1, 255, 244, bomLines);
       List<Order> orderList = orderMapper.getAllOrdersByUserId(1);
        assertEquals(2, orderList.size());


    }
}