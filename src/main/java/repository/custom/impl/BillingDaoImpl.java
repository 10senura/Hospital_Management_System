package repository.custom.impl;

import db.DBConnection;
import entity.BillingEntity;
import repository.custom.BillingDao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillingDaoImpl implements BillingDao {

    private static BillingDaoImpl billingDapImpl;
    private static final Logger LOGGER = Logger.getLogger(BillingDaoImpl.class.getName());

    private BillingDaoImpl() {
    }

    public static BillingDaoImpl getInstance() {
        if (billingDapImpl == null) {
            synchronized (BillingDaoImpl.class) {
                if (billingDapImpl == null) {
                    billingDapImpl = new BillingDaoImpl();
                }
            }
        }
        return billingDapImpl;
    }

    @Override
    public boolean save(BillingEntity entity) {
        String query = "INSERT INTO billing (patient_id, total_amount, payment_status, generated_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setInt(1, entity.getPatient_id());
            statement.setObject(2, entity.getTotal_amount());
            statement.setString(3, entity.getPayment_status());
            statement.setDate(4, Date.valueOf(entity.getGenerated_date()));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving billing record: " + entity, e);
            return false;
        }
    }

    @Override
    public BillingEntity search(String id) {
        String query = "SELECT * FROM billing WHERE bill_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new BillingEntity(
                            resultSet.getInt("bill_id"),
                            resultSet.getInt("patient_id"),
                            resultSet.getDouble("total_amount"),
                            resultSet.getString("payment_status"),
                            resultSet.getDate("generated_date").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching billing record with ID: " + id, e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM billing WHERE bill_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting billing record with ID: " + id, e);
            return false;
        }
    }

    @Override
    public boolean update(BillingEntity entity) {
        String query = "UPDATE billing SET patient_id = ?, total_amount = ?, payment_status = ?, generated_date = ? WHERE bill_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setInt(1, entity.getPatient_id());
            statement.setObject(2, entity.getTotal_amount());
            statement.setString(3, entity.getPayment_status());
            statement.setDate(4, Date.valueOf(entity.getGenerated_date()));
            statement.setInt(5, entity.getBill_id());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating billing record: " + entity, e);
            return false;
        }
    }

    @Override
    public List<BillingEntity> getAll() {
        String query = "SELECT * FROM billing";
        List<BillingEntity> billings = new ArrayList<>();
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                billings.add(new BillingEntity(
                        resultSet.getInt("bill_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getDouble("total_amount"),
                        resultSet.getString("payment_status"),
                        resultSet.getDate("generated_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all billing records", e);
        }
        return billings;
    }
}