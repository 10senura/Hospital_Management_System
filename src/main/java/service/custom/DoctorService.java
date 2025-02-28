package service.custom;

import dto.Doctor;
import dto.Patient;
import service.SuperService;

import java.util.List;

public interface DoctorService extends SuperService {
    List<Doctor> getDoctor();
    boolean addDoctor(Doctor doctor);
    Doctor getDoctorBYId(int doctor_id);
    boolean updateDoctor(Doctor doctor);
    Doctor getSearchDoctor(int doctor_id);
    boolean deleteDoctor(int doctor_id);
    List<Doctor> getAll();
}
