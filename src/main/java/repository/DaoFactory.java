package repository;

import repository.custom.impl.AppointmentDaoImpl;
import repository.custom.impl.DoctorDaoImpl;
import repository.custom.impl.MedicineDaoImpl;
import repository.custom.impl.PatientDaoImpl;
import util.DaoType;


public class DaoFactory {

    private static DaoFactory daoFactory;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    protected DaoFactory() {}

    public <T extends SuperDao> T getDao(DaoType daoType) {
        switch (daoType) {
            case PATIENT: return (T) PatientDaoImpl.getInstance();
            case DOCTOR: return (T) DoctorDaoImpl.getInstance();
            case APPOINTMENT: return (T) AppointmentDaoImpl.getInstance();
            case MEDICINE: return (T) MedicineDaoImpl.getInstance();
            default: return null;
        }
    }
}
