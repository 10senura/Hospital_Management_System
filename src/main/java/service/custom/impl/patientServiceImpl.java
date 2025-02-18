package service.custom.impl;

import dto.Patient;
import entity.PatientEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.PatientDao;
import service.custom.PatientService;
import util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;

public class patientServiceImpl implements PatientService {

    public static patientServiceImpl insance;

    PatientDao patientDao = DaoFactory.getInstance().getDaoType(DaoType.PATIENT);


    private patientServiceImpl() {
    }

    public static patientServiceImpl getInstance() {
        return insance == null ? insance = new patientServiceImpl() : insance;

    }

    @Override
    public boolean addPatient(Patient patient) throws SQLException {

        PatientEntity patientEntity = new ModelMapper().map(patient, PatientEntity.class);
        System.out.println(patientEntity.getPatient_id() + patientEntity.getName() + patientEntity.getGender());
        boolean save = patientDao.save(patientEntity);
        System.out.println(save);
        return save;
    }

    @Override
    public boolean deletePatient(Integer id) throws SQLException {
        return patientDao.delete(String.valueOf(id));

    }

    @Override
    public ArrayList<Patient> getAll() {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        patientDao.gettAll().forEach(patientEntity -> patientArrayList.add(new ModelMapper().map(patientEntity, Patient.class)));
        return patientArrayList;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return patientDao.update(new ModelMapper().map(patient, PatientEntity.class));
    }


    @Override
    public Patient searchPatient(Integer id) {
        return new ModelMapper().map(patientDao.search(String.valueOf(id)), Patient.class);
    }

    public Integer getNextId() {
        return patientDao.getNextId();

    }

    @Override
    public ArrayList<Patient> getPatientsID() {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        patientDao.getPatient_id().forEach(patientEntity -> patientArrayList.add(new ModelMapper().map(patientEntity, Patient.class)));
        return patientArrayList;
    }
}