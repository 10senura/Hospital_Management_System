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


    public void btnBackPatientViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/patient_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController1.getChildren().clear();
        this.lodeFormController1.getChildren().add(lode);
    }



    private void loadTable() {
        tblPatientView.getItems().clear();
        List<Patient> patients = patientService.getAll();
        if (patients != null) {
            ObservableList<Patient> observableList = FXCollections.observableArrayList(patients);
            tblPatientView.setItems(observableList);
        } else {
            new Alert(Alert.AlertType.WARNING, "No patient records found.").show();
        }
    }

    public void btnPatientSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnPatientDeleteOnAction(ActionEvent actionEvent) {
    }


    public void btnPatientUpdateOnAction(ActionEvent actionEvent) {
    }


    public void btnPatientDitalsAddOnAction(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmPatientId.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        clmPatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPatientAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clmPatientGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmPatientContact.setCellValueFactory(new PropertyValueFactory<>("contact_details"));
        clmPatientEmergencyContact.setCellValueFactory(new PropertyValueFactory<>("emergence_contact"));
        clmPatientMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("medical_history"));
        loadTable();
    }
}
