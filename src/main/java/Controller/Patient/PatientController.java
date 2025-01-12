package Controller.Patient;

import Controller.Model.Patient;
import javafx.collections.ObservableList;

public class PatientController implements PatientServices{


    @Override
    public boolean AddPatient(Patient patient) {
        return false;
    }

    @Override
    public boolean UpdatePatient(Patient patient) {
        return false;
    }

    @Override
    public boolean DeletePatient(String patient_id) {
        return false;
    }

    @Override
    public Patient SearchPatient(String name) {
        return null;
    }

    @Override
    public ObservableList<Patient> getAll() {
        return null;
    }
}
