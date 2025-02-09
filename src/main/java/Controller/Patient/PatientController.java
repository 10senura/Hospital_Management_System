package Controller.Patient;

import Controller.DB.DBConnection;
import Controller.Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientController implements PatientServices {

    private static PatientController instance;

    private PatientController() {
    }

    public static PatientController getInstance() {
        return instance == null ? instance = new PatientController() : instance;
    }

    @Override
    public boolean addPatient(Patient patient) {
        try {
            String SQL = "INSERT INTO patient VALUES(?,?,?,?,?,?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, patient.getPatient_id());
            psTm.setObject(2, patient.getName());
            psTm.setObject(3, patient.getAge());
            psTm.setObject(4, patient.getGender());
            psTm.setObject(5, patient.getEmergence_contact());
            psTm.setObject(6, patient.getMedical_history());
            return psTm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean UpdatePatient(Patient patient) {
        try {
            String SQL = "UPDATE patient SET name=?, age=?, gender=?, emergence_contact=?, medical_history=? WHERE patient_id=?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, patient.getName());
            psTm.setObject(2, patient.getAge());
            psTm.setObject(3, patient.getGender());
            psTm.setObject(4, patient.getEmergence_contact());
            psTm.setObject(5, patient.getMedical_history());
            psTm.setObject(6, patient.getPatient_id());
            return psTm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean DeletePatient(String patient_id) {
        String SQL = "DELETE FROM patient WHERE patient_id=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, patient_id);
            return psTm.executeUpdate() > 0;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public Patient SearchPatient(String name) {
        String SQL = "SELECT * FROM patient WHERE name=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL, name);
            if (resultSet.next()) {
                return new Patient(
                        resultSet.getString("patient_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("emergence_contact"),
                        resultSet.getString("medical_history")
                );
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return null;
    }

    @Override
    public ObservableList<Patient> getAll() {
        ObservableList<Patient> patientObservableList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM patient";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getString("patient_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("emergence_contact"),
                        resultSet.getString("medical_history")
                );
                patientObservableList.add(patient);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
        return patientObservableList;
    }
}