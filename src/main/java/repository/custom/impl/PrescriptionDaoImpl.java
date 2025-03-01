package repository.custom.impl;

import entity.PrescriptionEntity;
import repository.custom.PrescriptionDao;

import java.util.List;
import java.util.logging.Logger;

public class PrescriptionDaoImpl implements PrescriptionDao {

    private static PrescriptionDaoImpl prescriptionDaoImpl;
    private static final Logger LOGGER = Logger.getLogger(PrescriptionDaoImpl.class.getName());

    private PrescriptionDaoImpl() {
    }

    public static PrescriptionDaoImpl getInstance() {
        if (prescriptionDaoImpl == null) {
            synchronized (PrescriptionDaoImpl.class) {
                if (prescriptionDaoImpl == null) {
                    prescriptionDaoImpl = new PrescriptionDaoImpl();
                }
            }
        }
        return prescriptionDaoImpl;
    }

    @Override
    public boolean save(PrescriptionEntity entity) {
        return false;
    }

    @Override
    public PrescriptionEntity search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(PrescriptionEntity entity) {
        return false;
    }

    @Override
    public List<PrescriptionEntity> getAll() {
        return List.of();
    }
}
