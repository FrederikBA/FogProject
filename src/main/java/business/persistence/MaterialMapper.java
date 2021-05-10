package business.persistence;

import business.entities.Material;
import business.exceptions.UserException;

import java.sql.*;
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
                    String unit = rs.getString(3);
                    String description = rs.getString(4);
                    double pricePerUnit = rs.getDouble(5);

                    Material tmpMaterial = new Material(id, name, unit, description, pricePerUnit);
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

    public Material getMaterialById(int materialId) throws UserException {
        Material tmpMaterial = null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material WHERE id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, materialId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String unit = rs.getString(3);
                    String description = rs.getString(4);
                    double pricePerUnit = rs.getDouble(5);

                    tmpMaterial = new Material(id, name, unit, description, pricePerUnit);
                }
                return tmpMaterial;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
