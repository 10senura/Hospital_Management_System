package controller.Appointment;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AppointmentViewFormController {
    public AnchorPane AppomentlodeFormController;
    public AnchorPane lodeFormController2;
    public AnchorPane lodeFormController1;
    public AnchorPane lodeFormController;

    public void btnPageBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/appointment_view_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnPatientAddOnAction(ActionEvent actionEvent) {

    }

    public void btnAppomentAddOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void btnAppomentPageBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/appointment_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.AppomentlodeFormController.getChildren().clear();
        this.AppomentlodeFormController.getChildren().add(lode);
    }

    public void btnPatientPageBackOnAction(ActionEvent actionEvent) {
    }
}
