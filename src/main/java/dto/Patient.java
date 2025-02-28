package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {
    private Integer patient_id;
    private String name;
    private Integer age;
    private String gender;
    private String contact_details;
    private String emergency_contact;
    private String medical_history;

}
