package Controller.Patient;

import Controller.Model.Patient;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewPatientFormController implements Initializable {

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAge;
    public JFXTextField txtGender;
    public JFXTextField txtEmergenceContact;
    public JFXTextField txtMedicalHistory;
    public AnchorPane lodeFormController;

    @FXML
    private TableColumn<?, ?> clmPatientId;

    @FXML
    private TableColumn<?, ?> clmPatientName;

    @FXML
    private TableColumn<?, ?> clmPatientAge;

    @FXML
    private TableColumn<?, ?> clmPatientGender;

    private TableColumn<?, ?> clmPatientContact;

    @FXML
    private TableColumn<?, ?> clmPatientEmergencyContact;

    @FXML
    private TableColumn<?, ?> clmPatientMedicalHistory;

    @FXML
    private TableView<Patient> tblPatients;

    PatientServices service = PatientController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void setTextToValues(Patient newValue) {
        if (newValue != null) {
            txtId.setText(newValue.getPatient_id());
            txtName.setText(newValue.getName());
            txtAge.setText(String.valueOf(newValue.getAge()));
            txtGender.setText(newValue.getGender());
            txtEmergenceContact.setText(newValue.getEmergence_contact());
            txtMedicalHistory.setText(newValue.getMedical_history());
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    public void btnPatientAddOnAction(ActionEvent actionEvent) {
        Patient patient = new Patient(
                txtId.getText(),
                txtName.getText(),
                Integer.parseInt(txtAge.getText()),
                txtGender.getText(),
                txtEmergenceContact.getText(),
                txtMedicalHistory.getText()
        );

        if (service.addPatient(patient)) {
            new Alert(Alert.AlertType.INFORMATION, "Patient Added !!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Patient Not Added :(").show();
        }
        loadTable();
    }

    private void loadTable() {
        ObservableList<Patient> patientObservableList = service.getAll();
        tblPatients.setItems(patientObservableList);
    }

    public void btnPatientPageBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/patient_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }
}