package service.custom.impl;

import dto.Doctor;
import entity.DoctorEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.DoctorDao;
import service.custom.DoctorService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class doctorServiceImpl implements DoctorService {

    private static doctorServiceImpl DoctorServiceImpl;
    private final DoctorDao dao;
    private final ModelMapper modelMapper;

    private doctorServiceImpl() {
        dao = DaoFactory.getInstance().getDao(DaoType.DOCTOR);
        modelMapper = new ModelMapper();
    }

    public static doctorServiceImpl getInstance() {
        if (DoctorServiceImpl == null) {
            DoctorServiceImpl = new doctorServiceImpl();
        }
        return DoctorServiceImpl;
    }


    @Override
    public List<Doctor> getDoctor() {
        return List.of();
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        return false;
    }

    @Override
    public Doctor getPatientBYId(int doctor_id) {
        return null;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return false;
    }

    @Override
    public boolean deleteDoctor(int doctor_id) {
        return false;
    }

    @Override
    public List<Doctor> getAll() {
        List<DoctorEntity> doctorEntities = dao.getAll();
        List<Doctor> doctors = new ArrayList<>();
        for (DoctorEntity entity : doctorEntities) {
            doctors.add(modelMapper.map(entity, Doctor.class));
        }
        return doctors;
    }
}
