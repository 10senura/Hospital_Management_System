package repository.custom.impl;

import db.DBConnection;
import entity.MedicineEntity;
import repository.custom.MedicineDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicineDaoImpl implements MedicineDao {

    private static MedicineDaoImpl medicineDaoImpl;
    private static final Logger LOGGER = Logger.getLogger(MedicineDaoImpl.class.getName());

    private MedicineDaoImpl() {
    }

    public static MedicineDaoImpl getInstance() {
        if (medicineDaoImpl == null) {
            synchronized (MedicineDaoImpl.class) {
                if (medicineDaoImpl == null) {
                    medicineDaoImpl = new MedicineDaoImpl();
                }
            }
        }
        return medicineDaoImpl;
    }

    @Override
    public boolean save(MedicineEntity entity) {
        String query = "INSERT INTO medicine (name, category, price, stock_quantity, expiry_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getMedicine_name());
            statement.setString(2, entity.getCategory());
            statement.setDouble(3, entity.getPrice());
            statement.setInt(4, entity.getStock_quantity());
            statement.setDate(5, Date.valueOf(entity.getExpiry_date()));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving medicine: " + entity, e);
            return false;
        }
    }

    @Override
    public MedicineEntity search(String id) {
        String query = "SELECT * FROM medicine WHERE medicine_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new MedicineEntity(
                            resultSet.getInt("medicine_id"),
                            resultSet.getString("name"),
                            resultSet.getString("category"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("stock_quantity"),
                            resultSet.getDate("expiry_date").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching medicine with ID: " + id, e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM medicine WHERE medicine_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting medicine with ID: " + id, e);
            return false;
        }
    }

    @Override
    public boolean update(MedicineEntity entity) {
        String query = "UPDATE medicine SET name = ?, category = ?, price = ?, stock_quantity = ?, expiry_date = ? WHERE medicine_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getMedicine_name());
            statement.setString(2, entity.getCategory());
            statement.setDouble(3, entity.getPrice());
            statement.setInt(4, entity.getStock_quantity());
            statement.setDate(5, Date.valueOf(entity.getExpiry_date()));
            statement.setInt(6, entity.getMedicine_id());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating medicine: " + entity, e);
            return false;
        }
    }

    @Override
    public List<MedicineEntity> getAll() {
        String query = "SELECT * FROM medicine";
        List<MedicineEntity> medicines = new ArrayList<>();
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                medicines.add(new MedicineEntity(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("stock_quantity"),
                        resultSet.getDate("expiry_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all medicines", e);
        }
        return medicines;
    }
}
