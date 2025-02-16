package controller.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class MainDashBoardFormController {
    public AnchorPane lodeDashFormController;
    public AnchorPane lodeFormController;
    public TableView tblPatientView;
    public TableColumn clmPatientId;
    public TableColumn clmPatientName;
    public TableColumn clmPatientAge;
    public TableColumn clmPatientGender;
    public TableColumn clmPatientContact;
    public TableColumn clmPatientEmergencyContact;
    public TableColumn clmPatientMedicalHistory;

    public void btnDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/btn_dashboard_view.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/appointment_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnDoctorOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/doctor_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnMassageOnAction(ActionEvent actionEvent) {
    }

    public void btnSettingOnAction(ActionEvent actionEvent) {

    }

    public void btnPatientsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/patient_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Bye Bye! See you again.");
        alert.show();

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), stage.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(e -> stage.close());
        fadeOut.play();
    }

    public void btnBillingOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/billing_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    public void btnprescriptionOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/prescription_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }
}
