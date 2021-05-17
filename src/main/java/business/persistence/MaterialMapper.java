package business.persistence;

import business.entities.BomLine;
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
                    String description = rs.getString(2);
                    String unit = rs.getString(3);
                    double pricePerUnit = rs.getDouble(4);
                    String type = rs.getString(5);
                    Material tmpMaterial = new Material(id, description, unit, pricePerUnit, type);
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

    public List<Material> getAllWood() throws UserException {
        List<Material> woodMaterials = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material WHERE type = \"wood\"";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String description = rs.getString(2);
                    String unit = rs.getString(3);
                    double pricePerUnit = rs.getDouble(4);
                    String type = rs.getString(5);
                    Material tmpMaterial = new Material(id, description, unit, pricePerUnit, type);
                    woodMaterials.add(tmpMaterial);
                }
                return woodMaterials;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<Material> getAllAccesories() throws UserException {
        List<Material> accesories = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM material WHERE type = \"accesories\"";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String description = rs.getString(2);
                    String unit = rs.getString(3);
                    double pricePerUnit = rs.getDouble(4);
                    String type = rs.getString(5);
                    Material tmpMaterial = new Material(id, description, unit, pricePerUnit, type);
                    accesories.add(tmpMaterial);
                }
                return accesories;
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
                    String description = rs.getString(2);
                    String unit = rs.getString(3);
                    double pricePerUnit = rs.getDouble(4);
                    String type = rs.getString(5);

                    tmpMaterial = new Material(id, description, unit, pricePerUnit, type);
                }
                return tmpMaterial;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public int updateMaterialById(int materialId, double price) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE material SET price_per_unit = ? WHERE id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1, price);
                ps.setInt(2, materialId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");

        }
    }

    public void addMaterial(Material material) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO material (description,unit,price_per_unit,type) VALUES (?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, material.getDescription());
                ps.setString(2, material.getUnit());
                ps.setDouble(3, material.getPricePerUnit());
                ps.setString(4, material.getType());
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public int deleteMaterial(int materialId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM material WHERE id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, materialId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}