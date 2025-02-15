package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {
    private String appointment_id;
    private String patient_id;
    private String doctor_id;
    private String appointment_date;
    private String appointment_time;
    private String sdfsdf;
    private String drgd;

}
