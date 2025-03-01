package service.custom;

import dto.Medicine;
import service.SuperService;

import java.util.List;

public interface MedicineService extends SuperService {
    List<Medicine> getMedicine();
    boolean addMedicine(Medicine medicine);
    Medicine getMedicineBYId(int medicine_id);
    Medicine getSearchMedicine(int medicine_id);
    boolean updateMedicine(Medicine medicine);
    boolean deleteMedicine(String medicine_id);
    List<Medicine> getAll();
}
