package business.persistence;

import business.entities.BomLine;
import business.entities.Order;
import business.exceptions.UserException;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }

    public void createOrder(int userId, int length, int width, List<BomLine> bomLines) throws UserException {
        double orderPrice = 0;
        int orderId = 0;
        String status = "Forespørgsel";

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
            String sql = "INSERT INTO bom_items (order_id, material_id, name, quantity, length, unit, description, price) VALUES (?,?,?,?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, orderId);
                ps.setInt(2, line.getMaterialId());
                ps.setString(3, line.getName());
                ps.setInt(4, line.getQuantity());
                ps.setInt(5, line.getLength());
                ps.setString(6, line.getUnit());
                ps.setString(7, line.getDescription());
                ps.setDouble(8, line.getPrice());
                ps.executeUpdate();

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public int getOrderIdByTimestamp() throws UserException {
        int id = 0;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders ORDER BY timestamp DESC";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("order_id");
                }
                return id;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public double getOrderPriceByTimestamp() throws UserException {
        double price = 0;
        try (Connection connection = database.connect()) {
            String sql = "SELECT price FROM orders ORDER BY timestamp DESC";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    price = rs.getDouble("price");
                }
                return price;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public Order getOrder(int orderId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders WHERE order_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    double price = rs.getDouble("price");
                    String status = rs.getString("status");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    Order tmpOrder = new Order(orderId, userId, price, status, length, width, timestamp);
                    return tmpOrder;
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return null;
    }

    public void updateOrderStatusToPending(int orderId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE orders SET status = \"Behandles\" WHERE order_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public void updateOrderStatusToConfirmed(int orderId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE orders SET status = \"Bekræftet\" WHERE order_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    //Get orders from database
    public List<Order> getAllOrders() throws UserException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    int userId = rs.getInt("user_id");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    double price = rs.getDouble("price");
                    String status = rs.getString("status");

                    Order tmpOrder = new Order(orderId, userId, timestamp, price, status);
                    orderList.add(tmpOrder);
                }
                return orderList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    public List<Order> getAllOrdersByUserId(int userId) throws UserException {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders WHERE user_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    double price = rs.getDouble("price");
                    String status = rs.getString("status");

                    Order tmpOrder = new Order(orderId, userId, timestamp, price, status);
                    orderList.add(tmpOrder);
                }
                return orderList;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    public int deleteOrder(int orderId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM fog_carport.orders WHERE order_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public int deleteOrderContent(int orderId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM bom_items WHERE order_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
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
