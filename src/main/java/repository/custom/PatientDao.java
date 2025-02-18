package repository.custom;

import entity.PatientEntity;
import repository.CroudDao;

import java.util.ArrayList;

public interface PatientDao extends CroudDao<PatientEntity,String> {
    ArrayList<PatientEntity> getPatient_id();
}
