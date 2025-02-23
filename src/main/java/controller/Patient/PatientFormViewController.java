package controller.Patient;

import dto.Patient;
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
import service.ServiceFactory;
import service.custom.PatientService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PatientFormViewController implements Initializable {
    @FXML
    public AnchorPane lodeFormController1;
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
    public AnchorPane lodeFormController;
    public TextField txtSearchPatient;
    public TableColumn clmPatientDeleteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmPatientId.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        clmPatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPatientAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clmPatientGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmPatientContact.setCellValueFactory(new PropertyValueFactory<>("contact_details"));
        clmPatientEmergencyContact.setCellValueFactory(new PropertyValueFactory<>("emergence_contact"));
        clmPatientMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("medical_history"));

        clmPatientDeleteButton.setCellFactory(param -> new TableCell<Patient, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #3497f9; -fx-text-fill: white;");
                deleteButton.setOnMouseEntered(e -> deleteButton.setStyle("-fx-background-color: #ff1e00; -fx-text-fill: white;"));
                deleteButton.setOnMouseExited(e -> deleteButton.setStyle("-fx-background-color: #ff1e00; -fx-text-fill: white;"));

                deleteButton.setOnAction(event -> {
                    Patient patient = getTableRow().getItem();
                    if (patient != null) {
                        patientService.deletePatient(String.valueOf(patient));
                    }
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
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

    public void btnPatientAddPageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/patient_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController1.getChildren().clear();
        this.lodeFormController1.getChildren().add(lode);
    }

    private void loadForm(String fxmlPath) throws IOException {
        URL resource = this.getClass().getResource(fxmlPath);
        if (resource == null) {
            throw new IOException("FXML file not found: " + fxmlPath);
        }
        Parent form = FXMLLoader.load(resource);
        lodeFormController1.getChildren().clear();
        lodeFormController1.getChildren().add(form);
    }


}