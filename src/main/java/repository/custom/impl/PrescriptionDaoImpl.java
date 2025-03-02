package repository.custom.impl;

import db.DBConnection;
import entity.PrescriptionEntity;
import repository.custom.PrescriptionDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrescriptionDaoImpl implements PrescriptionDao {

    private static PrescriptionDaoImpl prescriptionDaoImpl;
    private static final Logger LOGGER = Logger.getLogger(PrescriptionDaoImpl.class.getName());

    private PrescriptionDaoImpl() {
    }

    public static PrescriptionDaoImpl getInstance() {
        if (prescriptionDaoImpl == null) {
            synchronized (PrescriptionDaoImpl.class) {
                if (prescriptionDaoImpl == null) {
                    prescriptionDaoImpl = new PrescriptionDaoImpl();
                }
            }
        }
        return prescriptionDaoImpl;
    }

    @Override
    public boolean save(PrescriptionEntity entity) {
        String query = "INSERT INTO prescription (patient_id, doctor_id, medicine, dosage, duration, qty) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getPatient_id());
            statement.setInt(2, entity.getDoctor_id());
            statement.setString(3, entity.getMedicine());
            statement.setString(4, entity.getDosage());
            statement.setString(5, entity.getDuration());
            statement.setInt(6, entity.getQty());

            boolean isSaved = statement.executeUpdate() > 0;
            if (isSaved) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    entity.setPrescription_id(generatedKeys.getInt(1)); // Auto-incremented ID set
                }
            }
            return isSaved;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving prescription: " + entity, e);
            return false;
        }
    }

    @Override
    public PrescriptionEntity search(String id) {
        String query = "SELECT * FROM prescription WHERE prescription_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new PrescriptionEntity(
                            resultSet.getInt("prescription_id"),
                            resultSet.getInt("patient_id"),
                            resultSet.getInt("doctor_id"),
                            resultSet.getString("medicine"),
                            resultSet.getString("dosage"),
                            resultSet.getString("duration"),
                            resultSet.getInt("qty")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching prescription with ID: " + id, e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM prescription WHERE prescription_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting prescription with ID: " + id, e);
            return false;
        }
    }

    @Override
    public boolean update(PrescriptionEntity entity) {
        String query = "UPDATE prescription SET patient_id = ?, doctor_id = ?, medicine = ?, dosage = ?, duration = ?, qty = ? WHERE prescription_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setInt(1, entity.getPatient_id());
            statement.setInt(2, entity.getDoctor_id());
            statement.setString(3, entity.getMedicine());
            statement.setString(4, entity.getDosage());
            statement.setString(5, entity.getDuration());
            statement.setInt(6, entity.getQty());
            statement.setInt(7,entity.getPrescription_id());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating prescription: " + entity, e);
            return false;
        }
    }

    @Override
    public List<PrescriptionEntity> getAll() {
        String query = "SELECT * FROM prescription";
        List<PrescriptionEntity> prescriptions = new ArrayList<>();
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                prescriptions.add(new PrescriptionEntity(
                        resultSet.getInt("prescription_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getString("medicine"),
                        resultSet.getString("dosage"),
                        resultSet.getString("duration"),
                        resultSet.getInt("qty")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all prescriptions", e);
        }
        return prescriptions;
    }
}
