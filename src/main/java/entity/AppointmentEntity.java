package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentEntity {
    private Integer appointment_id;
    private Integer patient_id;
    private Integer doctor_id;
    private LocalDate appointment_date;
    private LocalTime appointment_time;

}
