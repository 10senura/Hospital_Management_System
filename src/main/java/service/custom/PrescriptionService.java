package service.custom;

import dto.Prescription;
import service.SuperService;

import java.util.List;

public interface PrescriptionService extends SuperService {
    List<Prescription> getPrescription();
    boolean addPrescription(Prescription prescription);
    Prescription getPrescriptionBYId(int prescription_id);
    Prescription getSearchPrescription(int prescription_id);
    boolean updatePrescription(Prescription prescription);
    boolean deletePrescription(String prescription_id);
    List<Prescription> getAll();
}
