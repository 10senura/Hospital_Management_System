package service.custom.impl;

import dto.Patient;
import entity.PatientEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.PatientDao;
import service.custom.PatientService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class patientServiceImpl implements PatientService {
    private static patientServiceImpl patientServiceImpl;
    private final PatientDao dao;
    private final ModelMapper modelMapper;

    private patientServiceImpl() {
        dao = DaoFactory.getInstance().getDao(DaoType.PATIENT);
        modelMapper = new ModelMapper();
    }

    public static patientServiceImpl getInstance() {
        if (patientServiceImpl == null) {
            patientServiceImpl = new patientServiceImpl();
        }
        return patientServiceImpl;
    }

    @Override
    public List<Patient> getPatient() {
        List<PatientEntity> patientEntities = dao.getAll();
        List<Patient> patients = new ArrayList<>();
        for (PatientEntity entity : patientEntities) {
            patients.add(modelMapper.map(entity, Patient.class));
        }
        return patients;
    }

    @Override
    public boolean addPatient(Patient patient) {
        try {
            PatientEntity patientEntity = modelMapper.map(patient, PatientEntity.class);
            return dao.save(patientEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    }

    @Override
    public Patient getPatientBYId(int patient_id) {
        try {
            PatientEntity entity = dao.search(String.valueOf(patient_id));
            if (entity != null) {
                return modelMapper.map(entity, Patient.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Patient getSearchPatient(int id) {
        try {
            PatientEntity entity = dao.search(String.valueOf(id));
            if (entity != null) {
                return modelMapper.map(entity, Patient.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        try {
            PatientEntity patientEntity = modelMapper.map(patient, PatientEntity.class);
            return dao.update(patientEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePatient(String patient_id) {
        try {
            return dao.delete(String.valueOf(patient_id));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Patient> getAll() {
        List<PatientEntity> patientEntities = dao.getAll();
        List<Patient> patients = new ArrayList<>();
        for (PatientEntity entity : patientEntities) {
            patients.add(modelMapper.map(entity, Patient.class));
        }
        return patients;
    }
}