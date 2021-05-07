package business.persistence;

import business.entities.Material;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {
    private Database database;

    public MaterialMapper(Database database) {
        this.database = database;
    }

    public List<Material> getAllMaterials() throws UserException {
        List<Material> materialList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int length = rs.getInt(3);
                    String unit = rs.getString(4);
                    String description = rs.getString(5);
                    double pricePerUnit = rs.getDouble(6);

                    Material tmpMaterial = new Material(id, name, length, unit, description, pricePerUnit);
                    materialList.add(tmpMaterial);
                }
                return materialList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
