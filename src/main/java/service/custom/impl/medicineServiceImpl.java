package service.custom.impl;

import dto.Medicine;
import entity.MedicineEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.MedicineDao;
import service.custom.MedicineService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class medicineServiceImpl implements MedicineService {

    private static medicineServiceImpl MedicineServiceImpl;
    private final MedicineDao dao;
    private final ModelMapper modelMapper;

    private medicineServiceImpl() {
        dao = DaoFactory.getInstance().getDao(DaoType.MEDICINE);
        modelMapper = new ModelMapper();
    }

    public static medicineServiceImpl getInstance() {
        if (MedicineServiceImpl == null) {
            MedicineServiceImpl = new medicineServiceImpl();
        }
        return MedicineServiceImpl;
    }

    @Override
    public List<Medicine> getMedicine() {
        List<MedicineEntity> medicineEntities = dao.getAll();
        List<Medicine> medicines = new ArrayList<>();
        for (MedicineEntity entity : medicineEntities) {
            medicines.add(modelMapper.map(entity, Medicine.class));
        }
        return medicines;
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        try {
            MedicineEntity medicineEntity = modelMapper.map(medicine, MedicineEntity.class);
            return dao.save(medicineEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Medicine getMedicineBYId(int medicine_id) {
        try {
            MedicineEntity entity = dao.search(String.valueOf(medicine_id));
            if (entity != null) {
                return modelMapper.map(entity, Medicine.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Medicine getSearchMedicine(int medicine_id) {
        try {
            MedicineEntity entity = dao.search(String.valueOf(medicine_id));
            if (entity != null) {
                return modelMapper.map(entity, Medicine.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateMedicine(Medicine medicine) {
        try {
            MedicineEntity medicineEntity = modelMapper.map(medicine, MedicineEntity.class);
            return dao.update(medicineEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMedicine(String medicine_id) {
        try {
            return dao.delete(String.valueOf(medicine_id));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }    }

    @Override
    public List<Medicine> getAll() {
        List<MedicineEntity> medicineEntities = dao.getAll();
        List<Medicine> medicines = new ArrayList<>();
        for (MedicineEntity entity : medicineEntities) {
            medicines.add(modelMapper.map(entity, Medicine.class));
        }
        return medicines;
    }
}
