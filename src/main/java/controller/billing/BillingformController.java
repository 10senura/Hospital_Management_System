package controller.billing;

import com.jfoenix.controls.JFXComboBox;
import dto.Billing;
import dto.Patient;
import dto.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.BillingService;
import service.custom.PatientService;
import service.custom.impl.billingServiceImpl;
import service.custom.impl.patientServiceImpl;
import service.custom.impl.prescriptionServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class BillingformController implements Initializable {

    public AnchorPane lodeFormController2;
    public AnchorPane lodeFormController1;
    public AnchorPane lodeFormController;
    public TextField txtBillAmount;
    public DatePicker txtGenarateDate;
    public TextField txtPaymentStatus;
    public JFXComboBox<String> cmdBillId;
    public JFXComboBox<String> cmdPatientId;
    private final PatientService patientService = ServiceFactory.getInstance().getService(ServiceType.PATIENT);
    private final BillingService billingService = ServiceFactory.getInstance().getService(ServiceType.BILLING);

    public void btnBillViewBackOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBillingIds();
        cmdBillId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                search(newValue);
            }
        });
    }
    private void search(String id){
        Billing billing = billingServiceImpl.getInstance().getSearchBilling(Integer.parseInt(id));
        if (billing != null) {
            cmdPatientId.setValue(String.valueOf(billing.getPatient_id()));
            txtBillAmount.setText(String.valueOf(billing.getTotal_amount()));
            txtPaymentStatus.setText(billing.getPayment_status());
            txtGenarateDate.setValue(billing.getGenerated_date());
        } else {
            new Alert(Alert.AlertType.WARNING, "Patient not found!").show();
        }
    }

    public void loadBillingIds(){
        ObservableList<String> billingIds = FXCollections.observableArrayList();
        List<Billing> billings = Optional.ofNullable(billingService.getAll()).orElse(Collections.emptyList());
        for (Billing billing : billings) {
            billingIds.add(String.valueOf(billing.getPatient_id()));
        }
        cmdBillId.setItems(billingIds);
    }


    public void btnbillSubmitOnAction(ActionEvent actionEvent) {
        if (cmdPatientId.getValue() == null ||
                txtBillAmount.getText().isEmpty() ||
                txtPaymentStatus.getText().isEmpty() ||
                txtGenarateDate.getId().isEmpty())
        {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
            return;
        }

        Billing billing = new Billing(
                1,
                Integer.parseInt(cmdPatientId.getValue()),
                Double.parseDouble(txtBillAmount.getText()),
                txtPaymentStatus.getText(),
                LocalDate.now()
        );

        boolean isSaved = billingServiceImpl.getInstance().addBilling(billing);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Prescription saved successfully!").show();
            loadBillingIds();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save prescription!").show();
        }
    }

    public void btnBillUpdateOnAction(ActionEvent actionEvent) {
        if (cmdBillId.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Select a prescription to update!").show();
            return;
        }

        Billing billing = new Billing(
                Integer.parseInt(cmdBillId.getValue()),
                Integer.parseInt(cmdPatientId.getValue()),
                Double.parseDouble(txtBillAmount.getText()),
                txtPaymentStatus.getText(),
                txtGenarateDate.getValue()
        );

        boolean isUpdated = billingServiceImpl.getInstance().updateBilling(billing);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Billing updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update Billing!").show();
        }
    }

}
