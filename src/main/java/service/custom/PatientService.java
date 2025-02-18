package service.custom;

import javafx.collections.ObservableList;
import service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import dto.Patient;
import service.SuperService;
import java.util.List;

public interface  PatientService extends SuperService {

    boolean addPatient(Patient patient) throws SQLException;

    boolean deletePatient(Integer id) throws SQLException;

    ArrayList<Patient> getAll();

    boolean updatePatient(Patient patient);

    Patient searchPatient(Integer id);

    Integer getNextId();

    ArrayList<Patient>getPatientsID();

}
