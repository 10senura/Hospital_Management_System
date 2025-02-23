package controller.doctor;

import dto.Doctor;
import dto.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.DoctorService;
import service.custom.PatientService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewDoctorFormController implements Initializable {
    public AnchorPane lodeFormController;
    public TableView tblDoctorView;
    public TableColumn clmdoctor_id;
    public TableColumn clmDoctorName;
    public TableColumn clmDoctorspecialty;
    public TableColumn clmDoctorqualifications;
    public TableColumn clmDoctoravailability;
    public TableColumn clmDoctorContact;
    public AnchorPane DoctorlodeFormController;
    private final DoctorService doctorService = ServiceFactory.getInstance().getService(ServiceType.DOCTOR);

    public void btnDoctorAddPageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/doctor_register_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.DoctorlodeFormController.getChildren().clear();
        this.DoctorlodeFormController.getChildren().add(lode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmdoctor_id.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        clmDoctorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmDoctorspecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        clmDoctoravailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        clmDoctorqualifications.setCellValueFactory(new PropertyValueFactory<>("qualifications"));
        clmDoctorContact.setCellValueFactory(new PropertyValueFactory<>("contact_details"));
        loadTable();
    }

    private void loadTable() {
        tblDoctorView.getItems().clear();
        List<Doctor> doctors = doctorService.getAll();
        if (doctors != null) {
            ObservableList<Doctor> observableList = FXCollections.observableArrayList(doctors);
            tblDoctorView.setItems(observableList);
        } else {
            new Alert(Alert.AlertType.WARNING, "No patient records found.").show();
        }
    }
}
