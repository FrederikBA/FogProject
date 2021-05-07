package business.persistence;

import business.entities.BomLine;
import business.exceptions.UserException;

import java.sql.*;
import java.util.List;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }

    public void createOrder(int userId, int length, int width, List<BomLine> bomLines) throws UserException {
        double orderPrice = 0;
        int orderId = 0;
        String status = "negative";

        for (BomLine bl : bomLines) {
            orderPrice += bl.getPrice();
        }
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO orders (user_id,price,status,length,width) VALUES (?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, userId);
                ps.setDouble(2, orderPrice);
                ps.setString(3, status);
                ps.setInt(4, length);
                ps.setInt(5, width);


                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                orderId = ids.getInt(1);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

            for (BomLine bomLine : bomLines) {
                insertIntoBomItems(orderId, bomLine);
            }

        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public void insertIntoBomItems(int orderId, BomLine line) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO bom_items (order_id, material_id, quantity, length, description, price) VALUES (?,?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, orderId);
                ps.setInt(2, line.getMaterialId());
                ps.setInt(3, line.getQuantity());
                ps.setInt(4, line.getLength());
                ps.setString(5, line.getDescription());
                ps.setDouble(6, line.getPrice());
                ps.executeUpdate();

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}
