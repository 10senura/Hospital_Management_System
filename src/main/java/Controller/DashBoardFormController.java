package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {

    public AnchorPane lodeFormController;

    @FXML
    void btnDashBoardOnAction(ActionEvent event)  {

    }

    @FXML
    void btnAppointmentOnAction(ActionEvent event) {

    }

    @FXML
    void btnPatientsOnAction(ActionEvent event)throws IOException {
        URL resource = this.getClass().getResource("/view/patient_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    @FXML
    void btnDoctorOnAction(ActionEvent event) {

    }

    @FXML
    void btnMassageOnAction(ActionEvent event) {

    }

    @FXML
    void btnSettingOnAction(ActionEvent event) {

    }


    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }
}
