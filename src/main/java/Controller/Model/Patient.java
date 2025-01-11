package Controller.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {

   private String name;
   private int age;
   private String gender;
   private String emergence_contact;
   private String medical_history;

}
