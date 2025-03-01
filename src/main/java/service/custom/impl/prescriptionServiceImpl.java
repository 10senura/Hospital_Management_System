package service.custom.impl;

import dto.Patient;
import dto.Prescription;
import entity.PatientEntity;
import entity.PrescriptionEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.PatientDao;
import repository.custom.PrescriptionDao;
import service.custom.PrescriptionService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class prescriptionServiceImpl implements PrescriptionService {

    private static prescriptionServiceImpl prescriptionServiceImpl;
    private final PrescriptionDao dao;
    private final ModelMapper modelMapper;

    private prescriptionServiceImpl() {
        dao = DaoFactory.getInstance().getDao(DaoType.PRESCRIPTION);
        modelMapper = new ModelMapper();
    }

    public static prescriptionServiceImpl getInstance() {
        if (prescriptionServiceImpl == null) {
            prescriptionServiceImpl = new prescriptionServiceImpl();
        }
        return prescriptionServiceImpl;
    }

    @Override
    public List<Prescription> getPrescription() {
        List<PrescriptionEntity> prescriptionEntities = dao.getAll();
        List<Prescription> prescriptions = new ArrayList<>();
        for (PrescriptionEntity entity : prescriptionEntities) {
            prescriptions.add(modelMapper.map(entity, Prescription.class));
        }
        return prescriptions;
    }

    @Override
    public boolean addPrescription(Prescription prescription) {
        try {
            PrescriptionEntity prescriptionEntity = modelMapper.map(prescription, PrescriptionEntity.class);
            return dao.save(prescriptionEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Prescription getPrescriptionBYId(int prescription_id) {
        try {
            PrescriptionEntity entity = dao.search(String.valueOf(prescription_id));
            if (entity != null) {
                return modelMapper.map(entity, Prescription.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Prescription getSearchPrescription(int prescription_id) {
        try {
            PrescriptionEntity entity = dao.search(String.valueOf(prescription_id));
            if (entity != null) {
                return modelMapper.map(entity, Prescription.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePrescription(Prescription prescription) {
        try {
            PrescriptionEntity prescriptionEntity = modelMapper.map(prescription, PrescriptionEntity.class);
            return dao.update(prescriptionEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePrescription(String prescription_id) {
        try {
            return dao.delete(String.valueOf(prescription_id));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Prescription> getAll() {
        List<PrescriptionEntity> patientEntities = dao.getAll();
        List<Prescription> prescriptions = new ArrayList<>();
        for (PrescriptionEntity entity : patientEntities) {
            prescriptions.add(modelMapper.map(entity, Prescription.class));
        }
        return prescriptions;
    }

}
