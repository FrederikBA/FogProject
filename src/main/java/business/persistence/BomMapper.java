package business.persistence;

import business.entities.BomLine;
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
                    String desc = rs.getString("description");
                    double price = rs.getDouble("price");

                    BomLine tmpBomLine = new BomLine(materialId, name, quantity, length, desc, price);
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
}
