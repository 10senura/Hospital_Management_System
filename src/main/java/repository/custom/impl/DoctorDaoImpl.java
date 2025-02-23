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


public class DoctorDaoImpl  implements DoctorDao {

    private static  DoctorDaoImpl doctorDaoImpl ;
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
        return false;
    }

    @Override
    public DoctorEntity search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(DoctorEntity entity) {
        return false;
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
            LOGGER.log(Level.SEVERE, "Error retrieving all patients", e);
        }
        return doctors;
    }
}
