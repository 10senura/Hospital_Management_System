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
    private Integer appointment_id;
    private Integer patient_id;
    private Integer doctor_id;
    private String appointment_date;
    private String appointment_time;

}
