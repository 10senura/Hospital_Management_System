package controller.Patient;

import com.jfoenix.controls.JFXComboBox;
import dto.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.PatientService;
import service.custom.impl.patientServiceImpl;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class ViewPatientFormController implements Initializable  {
    public AnchorPane lodeFormController;
    public AnchorPane lodeFormController1;
    public TextField txtpatient_id;
    public TextField txtpatient_name;
    public TextField txtpatient_age;
    public TextField txtpatient_contact_D;
    public TextField txtpatient_emergency_c;
    public TextField txtpatient_medical_h;
    public TextField txtpatient_gender;

    @FXML
    public TableView<Patient> tblPatientView;
    public JFXComboBox <String> cmbPatientId;
    private final PatientService patientService = ServiceFactory.getInstance().getService(ServiceType.PATIENT);
    List<Patient> patientList = new ArrayList<>();

    public void btnBackPatientViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/patient_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController1.getChildren().clear();
        this.lodeFormController1.getChildren().add(lode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPatientIds();
        cmbPatientId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                search(newValue);
            }
        });
    }

    private void populateTable() {
        patientList.clear();
        patientList.addAll(patientService.getPatient());
        tblPatientView.setItems(FXCollections.observableList(patientList));
    }

    public void btnPatientUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (cmbPatientId.getValue() == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a patient to update!").show();
                return;
            }

            int id;
            try {
                id = Integer.parseInt(cmbPatientId.getValue());
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid Patient ID! Please select a valid patient.").show();
                return;
            }

            if (
                    txtpatient_name.getText().isEmpty() ||
                    txtpatient_age.getText().isEmpty() ||
                    txtpatient_gender.getText().isEmpty() ||
                    txtpatient_contact_D.getText().isEmpty() ||
                    txtpatient_emergency_c.getText().isEmpty() ||
                    txtpatient_medical_h.getText().isEmpty()) {

                new Alert(Alert.AlertType.WARNING, "Please fill in all fields before updating!").show();
                return;
            }

            int age;
            try {
                age = Integer.parseInt(txtpatient_age.getText());
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid age format! Please enter a valid number.").show();
                return;
            }

            Patient patient = new Patient(
                    id,
                    txtpatient_name.getText(),
                    age,
                    txtpatient_gender.getText(),
                    txtpatient_contact_D.getText(),
                    txtpatient_emergency_c.getText(),
                    txtpatient_medical_h.getText()
            );

            if (patientService.updatePatient(patient)) {
                new Alert(Alert.AlertType.INFORMATION, "Patient updated successfully!").show();
                populateTable();
                loadPatientIds();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed! Please try again.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }


    public void btnPatientDitalsAddOnAction(ActionEvent actionEvent) {
        Patient patient = new Patient(
                           1,
                txtpatient_name.getText(),
                Integer.parseInt(txtpatient_age.getText()),
                txtpatient_gender.getText(),
                txtpatient_contact_D.getText(),
                txtpatient_emergency_c.getText(),
                txtpatient_medical_h.getText()
        );

        if(patientService.addPatient(patient)){
            new Alert(Alert.AlertType.INFORMATION,"Added Successfully!");
        }else{
            new Alert(Alert.AlertType.ERROR,"Added Not Successfully ?");
        }
    }

    private void search(String id){
        Patient patient = patientServiceImpl.getInstance().getSearchPatient(Integer.parseInt(id));
        if (patient != null) {
            txtpatient_name.setText(patient.getName());
            txtpatient_age.setText(String.valueOf(patient.getAge()));
            txtpatient_gender.setText(patient.getGender());
            txtpatient_contact_D.setText(patient.getContact_details());
            txtpatient_emergency_c.setText(patient.getEmergency_contact());
            txtpatient_medical_h.setText(patient.getMedical_history());
        } else {
            new Alert(Alert.AlertType.WARNING, "Patient not found!").show();
        }
    }

    public void loadPatientIds(){
        ObservableList<String> patientIds = FXCollections.observableArrayList();
        List<Patient> patients = Optional.ofNullable(patientService.getAll()).orElse(Collections.emptyList());
        for (Patient patient : patients) {
            patientIds.add(String.valueOf(patient.getPatient_id()));
        }
        cmbPatientId.setItems(patientIds);
        }
}
