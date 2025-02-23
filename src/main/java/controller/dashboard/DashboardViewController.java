package controller.dashboard;

import db.DBConnection;
import dto.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.PatientService;
import util.ServiceType;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;


public class DashboardViewController  implements Initializable {

    public AnchorPane lodeFormController1;
    public AnchorPane lodeFormController;
    public TableView tblPatientView;
    public TableColumn clmPatientId;
    public TableColumn clmPatientName;
    public TableColumn clmPatientAge;
    public TableColumn clmPatientGender;
    public TableColumn clmPatientEmergencyContact;
    public TableColumn clmPatientContact;
    public TableColumn clmPatientMedicalHistory;
    private final PatientService patientService = ServiceFactory.getInstance().getService(ServiceType.PATIENT);
    public Label lblAppoinement;
    public Label lblDoctor;
    public Label lblBilling;
    public Label lblPatient;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            int appointmentCount = getCount(connection, "SELECT COUNT(*) FROM appointment");
            lblAppoinement.setText("    " + appointmentCount);

            int doctorCount = getCount(connection, "SELECT COUNT(*) FROM doctor");
            lblDoctor.setText("    " + doctorCount);

            int billingCount = getCount(connection, "SELECT COUNT(*) FROM billing");
            lblBilling.setText("    " + billingCount);

            int patientCount = getCount(connection, "SELECT COUNT(*) FROM patient");
            lblPatient.setText("    " + patientCount);

        } catch (Exception e) {
            e.printStackTrace();
        }

        clmPatientId.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        clmPatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPatientAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        clmPatientGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmPatientContact.setCellValueFactory(new PropertyValueFactory<>("contact_details"));
        clmPatientEmergencyContact.setCellValueFactory(new PropertyValueFactory<>("emergence_contact"));
        clmPatientMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("medical_history"));
        loadTable();
    }

    private int getCount(Connection connection, String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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

