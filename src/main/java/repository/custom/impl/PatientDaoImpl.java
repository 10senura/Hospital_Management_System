package repository.custom.impl;

import entity.PatientEntity;
import repository.custom.PatientDao;
import util.CroudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PatientDaoImpl implements PatientDao {
    private static final Logger LOGGER = Logger.getLogger(PatientDaoImpl.class.getName());

    @Override
    public boolean save(PatientEntity patientEntity)  throws SQLException {

        try {

            //  return CrudUtil.execute("INSERT INTO patient (name, age, gender, contact_details, emergency_contact, medical_history) VALUES (?,?,?,?,?,?);",
            return CroudUtil.execute("INSERT INTO patient VALUES(?,?,?,?,?,?,?)",
                    patientEntity.getPatient_id(),
                    patientEntity.getName(),
                    patientEntity.getAge(),
                    patientEntity.getGender(),
                    patientEntity.getContact_details(),
                    patientEntity.getEmergence_contact(),
                    patientEntity.getMedical_history()

            );

        } catch (SQLException e) {
            System.out.println("get");
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<PatientEntity> gettAll() {

        ArrayList<PatientEntity> patientList = new ArrayList<>();
        try {
            ResultSet resultSet = CroudUtil.execute("SELECT * FROM patient");

            while (resultSet.next()) {

                patientList.add(new PatientEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)

                ));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patientList;    }

    @Override
    public boolean update(PatientEntity entity) {
        return false;
    }

    @Override
    public PatientEntity search(String s) {
        return null;
    }

    @Override
    public Integer getNextId() {
        return 0;
    }

    @Override
    public ArrayList<PatientEntity> getPatient_id() {
        return null;
    }
}
