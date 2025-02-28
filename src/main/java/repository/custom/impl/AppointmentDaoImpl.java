package repository.custom.impl;

import db.DBConnection;
import entity.AppointmentEntity;
import entity.DoctorEntity;
import entity.PatientEntity;
import repository.custom.AppointmentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDaoImpl implements AppointmentDao {

    private static AppointmentDaoImpl appointmentDaoImpl;
    private static final Logger LOGGER = Logger.getLogger(AppointmentDaoImpl.class.getName());

    private AppointmentDaoImpl() {
    }

    public static AppointmentDaoImpl getInstance() {
        if (appointmentDaoImpl == null) {
            synchronized (AppointmentDaoImpl.class) {
                if (appointmentDaoImpl == null) {
                    appointmentDaoImpl = new AppointmentDaoImpl();
                }
            }
        }
        return appointmentDaoImpl;
    }


    @Override
    public boolean save(AppointmentEntity entity) {
        String query = "INSERT INTO appointment (patient_id, doctor_id, appointment_date, appointment_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setInt(1, entity.getPatient_id());
            statement.setInt(2, entity.getDoctor_id());
            statement.setDate(3, Date.valueOf(entity.getAppointment_date()));
            statement.setTime(4, Time.valueOf(entity.getAppointment_time().toLocalTime()));

            boolean result = statement.executeUpdate() > 0;

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setAppointment_id(generatedKeys.getInt(1));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving appointment: " + entity, e);
            return false;
        }
    }

    @Override
    public AppointmentEntity search(String id) {
        String query = "SELECT * FROM appointment WHERE appointment_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new AppointmentEntity(
                            resultSet.getInt("appointment_id"),
                            resultSet.getInt("patient_id"),
                            resultSet.getInt("doctor_id"),
                            resultSet.getDate("appointment_date").toLocalDate(),
                            resultSet.getTime("appointment_time")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error searching appointment with ID: " + id, e);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM appointment WHERE appointment_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(id));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting appointment with ID: " + id, e);
            return false;
        }
    }

    @Override
    public boolean update(AppointmentEntity entity) {
        String query = "UPDATE appointment SET patient_id = ?, doctor_id = ?, appointment_date = ?, appointment_time = ? WHERE appointment_id = ?";
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setInt(1, entity.getPatient_id());
            statement.setInt(2, entity.getDoctor_id());
            statement.setDate(3, Date.valueOf(entity.getAppointment_date()));
            statement.setTime(4, Time.valueOf(entity.getAppointment_time().toLocalTime()));
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating appointment: " + entity, e);
            return false;

        }
    }

    @Override
    public List<AppointmentEntity> getAll() {
        String query = "SELECT * FROM appointment";
        List<AppointmentEntity> appointment = new ArrayList<>();
        try (PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                appointment.add(new AppointmentEntity(
                        resultSet.getInt("appointment_id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getInt("doctor_id"),
                        resultSet.getDate("appointment_date").toLocalDate(),
                        resultSet.getTime("appointment_time")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all patients", e);
        }
        return appointment;
    }
}
