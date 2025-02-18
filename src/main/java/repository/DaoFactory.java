package repository;

import repository.custom.impl.PatientDaoImpl;
import util.DaoType;


public class DaoFactory  {
    private static DaoFactory instance;

    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType daoType){

        return null;
    }
}
