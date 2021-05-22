package business.persistence;

import business.entities.BomLine;
import business.entities.Material;
import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.BomService;
import business.services.MaterialFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaterialMapperTest {
    private final static String DATABASE = "fog_carport";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
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

            //Placed like this bcus of foregin key error with "Drop table if exists"
            stmt.execute("delete from bom_items");
            stmt.execute("delete from orders");
            stmt.execute("delete from user");
            stmt.execute("delete from material");

            //Creates 3 users, dont think i need this.
            stmt.execute(
                    "insert into user values " +
                            "(1,'jens@somewhere.com','jensen','customer'), " +
                            "(2,'ken@somewhere.com','kensen','customer'), " +
                            "(3,'robin@somewhere.com','batman','employee')");

            //Create three materials in DB
            stmt.execute(
                    "insert into material values " +
                            "(1, '25x200 mm. trykimp. Brædt', 'Stk', '100', 'wood'), " +
                            "(2, '25x125mm. trykimp. Brædt', 'Stk', '75', 'wood'), " +
                            "(3, '38x73 mm. Lægte ubh.', 'Stk', '115', 'wood')");

        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    //Test if it adds a material in db(from 3 to 4)
    @Test
    void addMaterial() throws UserException {
        Material material = new Material(1, "langt træ", "stk", 25.2, "wood");
        materialMapper.addMaterial(material);
        List<Material> materialList = materialMapper.getAllMaterials();
        assertNotNull(materialList);
        assertEquals(4,materialList.size());

    }
    @Test
    void getAllMaterials() {
    }

    @Test
    void updateMaterialById() {
    }

    @Test
    void deleteMaterial() throws UserException {
       Material tmp = materialMapper.getMaterialById(1);
        materialMapper.deleteMaterial(1);
        List<Material> materialList = materialMapper.getAllMaterials();
        assertEquals(2,materialList.size());
    }


}
