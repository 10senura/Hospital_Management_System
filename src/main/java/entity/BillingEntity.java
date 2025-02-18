package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillingEntity {
    private String bill_id;
    private String patient_id;
    private Double total_amount;
    private String payment_status;
    private String generated_date;
}
