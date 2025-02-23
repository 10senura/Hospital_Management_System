package service.custom;

import service.SuperService;

import java.util.List;

import dto.Patient;

public interface  PatientService extends SuperService {
    List<Patient> getPatient();
    boolean addPatient(Patient patient);
    Patient getPatientBYId(int patient_id);
    Patient getSearchPatient(int patient_id);
    boolean updatePatient(Patient patient);
    boolean deletePatient(String patient_id);
    List<Patient> getAll();
}
