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
    List<Patient> getPatient();
    boolean addPatient(Patient patient);
    Patient getPatientBYId(int patient_id);
    boolean updatePatient(Patient patient);
    boolean deletePatient(int patient_id);
    List<Patient> getAll();
}
