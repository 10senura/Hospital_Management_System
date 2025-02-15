package service.custom;

import javafx.collections.ObservableList;
import service.SuperService;
import java.util.List;
import javafx.collections.ObservableList;
import dto.Patient;
import service.SuperService;
import java.util.List;

public interface  PatientService extends SuperService {
    List<Patient> getAll();
    boolean saveCustomer(Patient patient);
    boolean updateCustomer(Patient patient);
    boolean deleteCustomer(String customerId);
    Patient searchCustomer(String customerId);
    ObservableList<String> getCustomerIds();
}
