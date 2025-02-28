package service.custom.impl;

import dto.Doctor;
import dto.Patient;
import entity.DoctorEntity;
import entity.PatientEntity;
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
        List<DoctorEntity> doctorEntities = dao.getAll();
        List<Doctor> doctors = new ArrayList<>();
        for (DoctorEntity entity : doctorEntities) {
            doctors.add(modelMapper.map(entity, Doctor.class));
        }
        return doctors;
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        try {
            DoctorEntity doctorEntity = modelMapper.map(doctor, DoctorEntity.class);
            return dao.save(doctorEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Doctor getDoctorBYId(int doctor_id) {
        try {
            DoctorEntity entity = dao.search(String.valueOf(doctor_id));
            if (entity != null) {
                return modelMapper.map(entity, Doctor.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        try {
            DoctorEntity doctorEntity = modelMapper.map(doctor, DoctorEntity.class);
            return dao.update(doctorEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Doctor getSearchDoctor(int doctor_id) {
        try {
            DoctorEntity entity = dao.search(String.valueOf(doctor_id));
            if (entity != null) {
                return modelMapper.map(entity, Doctor.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    }

    @Override
    public boolean deleteDoctor(int doctor_id) {
        try {
            return dao.delete(String.valueOf(doctor_id));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
