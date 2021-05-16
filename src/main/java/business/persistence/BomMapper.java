package business.persistence;

import business.entities.BomLine;
import business.entities.Material;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BomMapper {
    private Database database;

    public BomMapper(Database database) {
        this.database = database;
    }

    public List<BomLine> getBomByOrderId(int orderId) throws UserException {
        List<BomLine> bomLines = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM bom_items WHERE order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int materialId = rs.getInt("material_id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    int length = rs.getInt("length");
                    String unit = rs.getString("unit");
                    String desc = rs.getString("description");
                    double price = rs.getDouble("price");

                    BomLine tmpBomLine = new BomLine(materialId, name, quantity, length, unit, desc, price);
                    bomLines.add(tmpBomLine);
                }
                return bomLines;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public BomLine getBomLineByFromOrderByMaterialId(int orderId, int materialId) throws UserException {
        BomLine bomLine = null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM bom_items WHERE material_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.setInt(2, materialId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    //public BomLine(int materialId, String name, int quantity, int length, String unit, String description, double price)
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    int length = rs.getInt("length");
                    String unit = rs.getString("unit");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");

                    bomLine = new BomLine(materialId, name, quantity, length, unit, description, price);
                }
                return bomLine;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
