package controller.dashboard;

import dto.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.PatientService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainDashBoardFormController implements Initializable {
    public AnchorPane lodeDashFormController;
    public AnchorPane lodeFormController;
    public TableView tblPatientView;
    public TableColumn clmPatientId;
    public TableColumn clmPatientName;
    public TableColumn clmPatientAge;
    public TableColumn clmPatientGender;
    public TableColumn clmPatientContact;
    public TableColumn clmPatientEmergencyContact;
    public TableColumn clmPatientMedicalHistory;
    private final PatientService patientService = ServiceFactory.getInstance().getService(ServiceType.PATIENT);


    public void btnDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/btn_dashboard_view.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/appointment_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnDoctorOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/doctor_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }


    public void btnSettingOnAction(ActionEvent actionEvent) {

    }

    public void btnPatientsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/patient_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Bye Bye! See you again.");
        alert.show();

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), stage.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> stage.close());
        fadeOut.play();
    }

    public void btnBillingOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/billing_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnprescriptionOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/prescription_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        clmPatientId.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        clmPatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPatientAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clmPatientGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmPatientContact.setCellValueFactory(new PropertyValueFactory<>("contact_details"));
        clmPatientEmergencyContact.setCellValueFactory(new PropertyValueFactory<>("emergence_contact"));
        clmPatientMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("medical_history"));
        loadTable();
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

}
