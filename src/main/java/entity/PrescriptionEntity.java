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
    private Integer prescription_id;
    private Integer patient_id;
    private Integer doctor_id;
    private String medicine;
    private String dosage;
    private String duration;
    private Integer qty;

}
