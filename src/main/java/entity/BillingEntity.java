package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillingEntity {
    private Integer bill_id;
    private Integer patient_id;
    private Double total_amount;
    private String payment_status;
    private LocalDate generated_date;


}
