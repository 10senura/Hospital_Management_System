package controller.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ViewDoctorFormController {
    public AnchorPane lodeFormController;
    public TableView tblDoctorView;
    public TableColumn clmdoctor_id;
    public TableColumn clmDoctorName;
    public TableColumn clmDoctorspecialty;
    public TableColumn clmDoctorqualifications;
    public TableColumn clmDoctoravailability;
    public TableColumn clmDoctorContact;
    public AnchorPane DoctorlodeFormController;

    public void btnDoctorAddPageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/doctor_register_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.DoctorlodeFormController.getChildren().clear();
        this.DoctorlodeFormController.getChildren().add(lode);
    }
}
