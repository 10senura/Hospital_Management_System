package service.custom.impl;

import dto.Appointment;
import dto.Patient;
import entity.AppointmentEntity;
import entity.PatientEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.AppointmentDao;
import service.SuperService;
import service.custom.AppointmentService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class appointmentServiceImpl implements AppointmentService, SuperService {

    private static appointmentServiceImpl AppointmentServiceImpl;
    private final AppointmentDao dao;
    private final ModelMapper modelMapper;

    private appointmentServiceImpl() {
        dao = DaoFactory.getInstance().getDao(DaoType.APPOINTMENT);
        modelMapper = new ModelMapper();
    }

    public static appointmentServiceImpl getInstance() {
        if (AppointmentServiceImpl == null) {
            AppointmentServiceImpl = new appointmentServiceImpl();
        }
        return AppointmentServiceImpl;
    }


    @Override
    public List<Appointment> getAppointment() {
        List<AppointmentEntity> appointmentEntities = dao.getAll();
        List<Appointment> appointments = new ArrayList<>();
        for (AppointmentEntity entity : appointmentEntities) {
            appointments.add(modelMapper.map(entity, Appointment.class));
        }
        return appointments;
    }

    @Override
    public boolean addAppointment(Appointment appointment) {
        try {
            AppointmentEntity appointmentEntity = modelMapper.map(appointment, AppointmentEntity.class);
            return dao.save(appointmentEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     }

    @Override
    public Appointment getAppointment(int appointment_id) {
        try {
            AppointmentEntity entity = dao.search(String.valueOf(appointment_id));
            if (entity != null) {
                return modelMapper.map(entity, Appointment.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Appointment getSearchAppointment(int appointment_id) {
        try {
            AppointmentEntity entity = dao.search(String.valueOf(appointment_id));
            if (entity != null) {
                return modelMapper.map(entity, Appointment.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        try {
            AppointmentEntity appointmentEntity = modelMapper.map(appointment, AppointmentEntity.class);
            return dao.update(appointmentEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }     }

    @Override
    public boolean deleteAppointment(String appointment_id) {
        try {
            return dao.delete(String.valueOf(appointment_id));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Appointment> getAll() {
        List<AppointmentEntity> appointmentEntities = dao.getAll();
        List<Appointment> appointments = new ArrayList<>();
        for (AppointmentEntity entity : appointmentEntities) {
            appointments.add(modelMapper.map(entity, Appointment.class));
        }
        return appointments;
    }
}
