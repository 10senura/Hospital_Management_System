package controller.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ViewPatientFormController {
    public AnchorPane lodeFormController;
    public AnchorPane lodeFormController1;

    public void btnPatientAddOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/patient_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnBackPatientViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/patient_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController1.getChildren().clear();
        this.lodeFormController1.getChildren().add(lode);
    }
}
