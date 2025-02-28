package repository.custom.impl;

import db.DBConnection;
import entity.DoctorEntity;
import repository.custom.DoctorDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDaoImpl implements DoctorDao {

    private static DoctorDaoImpl doctorDaoImpl;
    private static final Logger LOGGER = Logger.getLogger(DoctorDaoImpl.class.getName());

    private DoctorDaoImpl() {
    }

    public static DoctorDaoImpl getInstance() {
        if (doctorDaoImpl == null) {
            synchronized (DoctorDaoImpl.class) {
                if (doctorDaoImpl == null) {
                    doctorDaoImpl = new DoctorDaoImpl();
                }
            }
        }
        return doctorDaoImpl;
    }

    @Override
    public boolean save(DoctorEntity entity) {
        String query = "INSERT INTO doctor (name, specialty, availability, qualifications, contact_details) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSpecialty());
            statement.setString(3, entity.getAvailability());
            statement.setString(4, entity.getQualifications());
            statement.setString(5, entity.getContact_details());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving doctor: " + entity, e);
            return false;
        }
    }

    @Override
    public DoctorEntity search(String id) {
        String query = "SELECT * FROM doctor WHERE doctor_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new DoctorEntity(
                            resultSet.getInt("doctor_id"),
                            resultSet.getString("name"),
                            resultSet.getString("specialty"),
                            resultSet.getString("availability"),
                            resultSet.getString("qualifications"),
                            resultSet.getString("contact_details")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching doctor with ID: " + id, e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM doctor WHERE doctor_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting doctor with ID: " + id, e);
            return false;
        }
    }

    @Override
    public boolean update(DoctorEntity entity) {
        String query = "UPDATE doctor SET name = ?, specialty = ?, availability = ?, qualifications = ?, contact_details = ? WHERE doctor_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSpecialty());
            statement.setString(3, entity.getAvailability());
            statement.setString(4, entity.getQualifications());
            statement.setString(5, entity.getContact_details());
            statement.setInt(6, entity.getDoctor_id());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating doctor: " + entity, e);
            return false;
        }
    }

    @Override
    public List<DoctorEntity> getAll() {
        String query = "SELECT * FROM doctor";
        List<DoctorEntity> doctors = new ArrayList<>();
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                doctors.add(new DoctorEntity(
                        resultSet.getInt("doctor_id"),
                        resultSet.getString("name"),
                        resultSet.getString("specialty"),
                        resultSet.getString("availability"),
                        resultSet.getString("qualifications"),
                        resultSet.getString("contact_details")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all doctors", e);
        }
        return doctors;
    }
}
