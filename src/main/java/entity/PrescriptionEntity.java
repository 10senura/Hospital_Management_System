package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrescriptionEntity {
    private String prescription_id;
    private String patient_id;
    private String doctor_id;
    private String medicine;
    private String dosage;
    private String duration;
}
