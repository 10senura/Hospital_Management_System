package controller.Patient;

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
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



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
    public TextField txtSearchPatient;

    @FXML
    public TableView<Patient> tblPatientView;
    @FXML
    public TableColumn<Patient, String> clmPatientId;
    @FXML
    public TableColumn<Patient, String> clmPatientName;
    @FXML
    public TableColumn<Patient, Integer> clmPatientAge;
    @FXML
    public TableColumn<Patient, String> clmPatientGender;
    @FXML
    public TableColumn<Patient, String> clmPatientContact;
    @FXML
    public TableColumn<Patient, String> clmPatientEmergencyContact;
    @FXML
    public TableColumn<Patient, String> clmPatientMedicalHistory;

    private final PatientService patientService = ServiceFactory.getInstance().getService(ServiceType.PATIENT);
    List<Patient> patientList = new ArrayList<>();

    public void btnBackPatientViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/patient_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController1.getChildren().clear();
        this.lodeFormController1.getChildren().add(lode);
    }


    public void btnPatientSearchOnAction(ActionEvent actionEvent) {

    }
    private void populateTable() {
        patientList.clear();
        patientList.addAll(patientService.getPatient());
        tblPatientView.setItems(FXCollections.observableList(patientList));
    }

    public void btnPatientDeleteOnAction(ActionEvent actionEvent) {
    }


    public void btnPatientUpdateOnAction(ActionEvent actionEvent) {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void setTextToValues(Patient newValue) {
        txtpatient_id.setText(String.valueOf(newValue.getPatient_id()));
        txtpatient_name.setText(newValue.getName());
        txtpatient_age.setText(String.valueOf(newValue.getAge()));
        txtpatient_gender.setText(newValue.getGender());
        txtpatient_contact_D.setText(newValue.getContact_details());
        txtpatient_emergency_c.setText(newValue.getEmergence_contact());
        txtpatient_medical_h.setText(newValue.getMedical_history());
    }

}
