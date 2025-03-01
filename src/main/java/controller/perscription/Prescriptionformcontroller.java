package controller.perscription;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class Prescriptionformcontroller {
    public AnchorPane lodeFormController1;
    public AnchorPane lodeFormController2;
    public AnchorPane lodeFormController;
    public JFXTextArea txtAreaMedicine;
    public TextField txtDosage;
    public TextField txtDuration;
    public JFXComboBox cmbprescriptionid;
    public JFXComboBox cmbpatientid;
    public JFXComboBox cmddoctorid;
    public AnchorPane MedicineloardFormController;

    public void btnPatientAddOnAction(ActionEvent actionEvent) {
    }

    public void btnPreScriptionSubmitOnAction(ActionEvent actionEvent) {
    }

    public void btnMedicineViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/medicine_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.MedicineloardFormController.getChildren().clear();
        this.MedicineloardFormController.getChildren().add(lode);
    }
}
