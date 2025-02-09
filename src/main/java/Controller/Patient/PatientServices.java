package Controller.Patient;

import Controller.Model.Patient;
import javafx.collections.ObservableList;

public interface PatientServices {

    boolean addPatient(Patient patient);

    boolean UpdatePatient(Patient patient);

    boolean DeletePatient(String patient_id);

    Patient SearchPatient(String name);

    ObservableList<Patient> getAll();

}
