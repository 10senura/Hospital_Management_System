package repository.custom.impl;

import db.DBConnection;
import entity.PatientEntity;
import repository.custom.PatientDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientDaoImpl implements PatientDao {

    private static PatientDaoImpl patientDaoImpl;
    private static final Logger LOGGER = Logger.getLogger(PatientDaoImpl.class.getName());

    private PatientDaoImpl() {
    }

    public static PatientDaoImpl getInstance() {
        if (patientDaoImpl == null) {
            synchronized (PatientDaoImpl.class) {
                if (patientDaoImpl == null) {
                    patientDaoImpl = new PatientDaoImpl();
                }
            }
        }
        return patientDaoImpl;
    }

    @Override
    public boolean save(PatientEntity entity) {
        String query = "INSERT INTO patient (name, age, gender, contact_details, emergency_contact, medical_history) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAge());
            statement.setString(3, entity.getGender());
            statement.setString(4, entity.getContact_details());
            statement.setString(5, entity.getEmergency_contact());
            statement.setString(6, entity.getMedical_history());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving patient: " + entity, e);
            return false;
        }
    }

    @Override
    public PatientEntity search(String id) {
        String query = "SELECT * FROM patient WHERE patient_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new PatientEntity(
                            resultSet.getInt("patient_id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("gender"),
                            resultSet.getString("contact_details"),
                            resultSet.getString("emergency_contact"),
                            resultSet.getString("medical_history")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching patient with ID: " + id, e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM patient WHERE patient_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting patient with ID: " + id, e);
            return false;
        }
    }

    @Override
    public boolean update(PatientEntity entity) {
        String query = "UPDATE patient SET name = ?, age = ?, gender = ?, contact_details = ?, emergency_contact = ?, medical_history = ? WHERE patient_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getAge());
            statement.setString(3, entity.getGender());
            statement.setString(4, entity.getContact_details());
            statement.setString(5, entity.getEmergency_contact());
            statement.setString(6, entity.getMedical_history());
            statement.setInt(7, entity.getPatient_id());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating patient: " + entity, e);
            return false;
        }
    }

    @Override
    public List<PatientEntity> getAll() {
        String query = "SELECT * FROM patient";
        List<PatientEntity> patients = new ArrayList<>();
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                patients.add(new PatientEntity(
                        resultSet.getInt("patient_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("contact_details"),
                        resultSet.getString("emergency_contact"),
                        resultSet.getString("medical_history")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all patients", e);
        }
        return patients;
    }
}
