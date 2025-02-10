package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {

    @FXML
    private TableColumn<?, ?> clmPatientAge;

    @FXML
    private TableColumn<?, ?> clmPatientContact;

    @FXML
    private TableColumn<?, ?> clmPatientEmergencyContact;

    @FXML
    private TableColumn<?, ?> clmPatientGender;

    @FXML
    private TableColumn<?, ?> clmPatientId;

    @FXML
    private TableColumn<?, ?> clmPatientMedicalHistory;

    @FXML
    private TableColumn<?, ?> clmPatientName;

    @FXML
    private AnchorPane lodeFormController;

    @FXML
    private TableView <?> tblPatientView;



    @FXML
    void btnAppointmentOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/appointment_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/btn_dashboard_view.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    @FXML
    void btnDoctorOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/doctor_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void btnMassageOnAction(ActionEvent event) {

    }

    @FXML
    void btnPatientsOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/patient_form_view_controller.fxml");
        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {

    }

}
