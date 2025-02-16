package controller.doctor;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DoctorRegisterFormController {
    public AnchorPane lodeFormController;
    public AnchorPane DoctorlodeFormController;

    public void btnDoctorRegisterOnAction(ActionEvent actionEvent) {

    }

    public void btnBackDoctorViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/doctor_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.DoctorlodeFormController.getChildren().clear();
        this.DoctorlodeFormController.getChildren().add(lode);
    }
}
