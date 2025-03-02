package controller.perscription;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import dto.Doctor;
import dto.Patient;
import dto.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.DoctorService;
import service.custom.PatientService;
import service.custom.PrescriptionService;
import service.custom.impl.patientServiceImpl;
import service.custom.impl.prescriptionServiceImpl;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Prescriptionformcontroller implements Initializable {
    public AnchorPane lodeFormController1;
    public AnchorPane lodeFormController2;
    public AnchorPane lodeFormController;
    public JFXTextArea txtAreaMedicine;
    public TextField txtDosage;
    public TextField txtDuration;
    public JFXComboBox <String> cmbprescriptionid;
    public JFXComboBox <String> cmbpatientid;
    public JFXComboBox <String> cmddoctorid;
    public AnchorPane MedicineloardFormController;
    public TextField txtQty;
    private final PrescriptionService prescriptionService = ServiceFactory.getInstance().getService(ServiceType.PRESCRIPTION);
    private final PatientService patientService = ServiceFactory.getInstance().getService(ServiceType.PATIENT);
    private final DoctorService doctorService = ServiceFactory.getInstance().getService(ServiceType.DOCTOR);

    public void btnMedicineViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/medicine_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.MedicineloardFormController.getChildren().clear();
        this.MedicineloardFormController.getChildren().add(lode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPrescriptionIds();
        loadDoctorIds();
        loadPatientIds();
        cmbprescriptionid.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                search(newValue);
            }
        });
        cmbpatientid.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                search(newValue);
            }
        });
        cmddoctorid.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                search(newValue);
            }
        });

    }
    private void search(String id){
        Prescription prescription = prescriptionServiceImpl.getInstance().getPrescriptionBYId(Integer.parseInt(id));
        if (prescription != null) {
            cmbpatientid.setValue(String.valueOf(prescription.getPatient_id()));
            cmddoctorid.setValue(String.valueOf(prescription.getDoctor_id()));
            txtAreaMedicine.setText(prescription.getMedicine());
            txtDosage.setText(String.valueOf(prescription.getDosage()));
            txtDuration.setText(prescription.getDuration());
            txtQty.setText(String.valueOf(prescription.getQty()));
        } else {
            new Alert(Alert.AlertType.WARNING, "Patient not found!").show();
        }
    }

    public void btnpreScriptionViewBackOnAction(ActionEvent actionEvent) {

    }


    public void btnPreScriptionSubmitOnAction(ActionEvent actionEvent) {
        if (cmbpatientid.getValue() == null || cmddoctorid.getValue() == null || txtAreaMedicine.getText().isEmpty()
                || txtDosage.getText().isEmpty() || txtDuration.getText().isEmpty() || txtQty.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
            return;
        }

        Prescription prescription = new Prescription(
                1,
                Integer.parseInt(cmbpatientid.getValue()),
                Integer.parseInt(cmddoctorid.getValue()),
                txtAreaMedicine.getText(),
                txtDosage.getText(),
                txtDuration.getText(),
                Integer.parseInt(txtQty.getText())
        );

        boolean isSaved = prescriptionServiceImpl.getInstance().addPrescription(prescription);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Prescription saved successfully!").show();
            loadPrescriptionIds();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save prescription!").show();
        }
    }

    public void btnUpdatePreScriptionOnAction(ActionEvent actionEvent) {
        if (cmbprescriptionid.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Select a prescription to update!").show();
            return;
        }

        Prescription prescription = new Prescription(
                Integer.parseInt(cmbprescriptionid.getValue()),
                Integer.parseInt(cmbpatientid.getValue()),
                Integer.parseInt(cmddoctorid.getValue()),
                txtAreaMedicine.getText(),
                txtDosage.getText(),
                txtDuration.getText(),
                Integer.parseInt(txtQty.getText())
        );

        boolean isUpdated = prescriptionServiceImpl.getInstance().updatePrescription(prescription);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Prescription updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update prescription!").show();
        }
    }

    public void loadPrescriptionIds(){
        ObservableList<String> prescriptionIds = FXCollections.observableArrayList();
        List<Prescription> prescriptions = Optional.ofNullable(prescriptionService.getAll()).orElse(Collections.emptyList());
        for (Prescription prescription : prescriptions) {
            prescriptionIds.add(String.valueOf(prescription.getPatient_id()));
        }
        cmbprescriptionid.setItems(prescriptionIds);
    }

    public void loadPatientIds(){
        ObservableList<String> patientIds = FXCollections.observableArrayList();
        List<Patient> patients = Optional.ofNullable(patientService.getAll()).orElse(Collections.emptyList());
        for (Patient patient : patients) {
            patientIds.add(String.valueOf(patient.getPatient_id()));
        }
        cmbpatientid.setItems(patientIds);
    }

    public void loadDoctorIds(){
        ObservableList<String> doctorIds = FXCollections.observableArrayList();
        List<Doctor> doctors = Optional.ofNullable(doctorService.getAll()).orElse(Collections.emptyList());
        for (Doctor doctor : doctors) {
            doctorIds.add(String.valueOf(doctor.getDoctor_id()));
        }
        cmddoctorid.setItems(doctorIds);
    }


}