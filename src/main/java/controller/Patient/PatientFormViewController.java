package controller.Patient;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

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

    public void btnPatientAddPageOnAction(ActionEvent actionEvent) {
    }
}
