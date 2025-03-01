package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MedicineEntity {
    private Integer medicine_id;
    private String medicine_name;
    private String category;
    private Double price;
    private Integer stock_quantity;
    private LocalDate expiry_date;
}
