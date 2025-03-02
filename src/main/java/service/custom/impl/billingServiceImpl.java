package service.custom.impl;

import dto.Billing;
import entity.BillingEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.BillingDao;
import repository.custom.DoctorDao;
import service.custom.BillingService;
import service.custom.DoctorService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class billingServiceImpl implements BillingService {

    private static billingServiceImpl BillingServiceImpl;
    private final BillingDao dao;
    private final ModelMapper modelMapper;

    private billingServiceImpl() {
        dao = DaoFactory.getInstance().getDao(DaoType.BILLING);
        modelMapper = new ModelMapper();
    }

    public static billingServiceImpl getInstance() {
        if (BillingServiceImpl == null) {
            BillingServiceImpl = new billingServiceImpl();
        }
        return BillingServiceImpl;
    }

    @Override
    public List<Billing> getBilling() {
        List<BillingEntity> billingEntities = dao.getAll();
        List<Billing> billings = new ArrayList<>();
        for (BillingEntity entity : billingEntities) {
            billings.add(modelMapper.map(entity, Billing.class));
        }
        return billings;
    }

    @Override
    public boolean addBilling(Billing billing) {
        try {
            BillingEntity billingEntity = modelMapper.map(billing, BillingEntity.class);
            return dao.save(billingEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Billing getBillingBYId(int bill_id) {
        try {
            BillingEntity entity = dao.search(String.valueOf(bill_id));
            if (entity != null) {
                return modelMapper.map(entity, Billing.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Billing getSearchBilling(int bill_id) {
        try {
            BillingEntity entity = dao.search(String.valueOf(bill_id));
            if (entity != null) {
                return modelMapper.map(entity, Billing.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateBilling(Billing billing) {
        try {
            BillingEntity billingEntity = modelMapper.map(billing, BillingEntity.class);
            return dao.update(billingEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBilling(String bill_id) {
        try {
            return dao.delete(bill_id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Billing> getAll() {
        List<BillingEntity> billingEntities = dao.getAll();
        List<Billing> billings = new ArrayList<>();
        for (BillingEntity entity : billingEntities) {
            billings.add(modelMapper.map(entity, Billing.class));
        }
        return billings;
    }
}
