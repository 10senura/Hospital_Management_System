package controller.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class PatientFormViewController {
    public AnchorPane lodeFormController1;
    public AnchorPane lodeFormController;
    public TableView tblPatientView;
    public TableColumn clmPatientId;
    public TableColumn clmPatientName;
    public TableColumn clmPatientAge;
    public TableColumn clmPatientGender;
    public TableColumn clmPatientContact;
    public TableColumn clmPatientEmergencyContact;
    public TableColumn clmPatientMedicalHistory;

    public void btnPatientAddPageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/patient_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController1.getChildren().clear();
        this.lodeFormController1.getChildren().add(lode);
    }
}
