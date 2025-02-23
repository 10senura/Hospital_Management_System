package repository.custom.impl;

import db.DBConnection;
import entity.AppointmentEntity;
import entity.DoctorEntity;
import repository.custom.AppointmentDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return false;
    }

    @Override
    public AppointmentEntity search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(AppointmentEntity entity) {
        return false;
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
                        resultSet.getString("appointment_date"),
                        resultSet.getString("appointment_time")
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all patients", e);
        }
        return appointment;
    }
}
