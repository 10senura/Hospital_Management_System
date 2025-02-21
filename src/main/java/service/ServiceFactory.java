package service;

import util.ServiceType;
import service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    private ServiceFactory() {}

    public <T extends SuperService> T getService(ServiceType serviceType) {
        switch (serviceType) {
            case PATIENT: return (T) patientServiceImpl.getInstance();
            default: return null;
        }
    }



}