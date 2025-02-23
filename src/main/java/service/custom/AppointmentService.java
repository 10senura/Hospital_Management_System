package service.custom;

import dto.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointment();
    boolean addAppointment(Appointment appointment);
    Appointment getAppointment(int appointment_id );
    boolean updateAppointment(Appointment appointment);
    boolean deleteAppointment(String appointment_id);
    List<Appointment> getAll();
}
