package service.custom;

import dto.Billing;
import service.SuperService;

import java.util.List;

public interface BillingService extends SuperService {
    List<Billing> getBilling();
    boolean addBilling(Billing billing);
    Billing getBillingBYId(int bill_id);
    Billing getSearchBilling(int bill_id);
    boolean updateBilling(Billing patient);
    boolean deleteBilling(String bill_id);
    List<Billing> getAll();
}
