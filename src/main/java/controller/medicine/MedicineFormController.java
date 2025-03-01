package controller.medicine;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class MedicineFormController {
    public AnchorPane medicineLoadFormController;
    public AnchorPane lodeFormController;
    public JFXComboBox cmbmedicineId;
    public TextField txtmedicineName;
    public TextField txtM_Category;
    public TextField txtM_price;
    public TextField txtM_stock;
    public TextField txtM_ExpireDate;

    public void btnMedicineSubmitOnAction(ActionEvent actionEvent) {

    }

    public void btnBackAddMedicinePageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/medicine_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.medicineLoadFormController.getChildren().clear();
        this.medicineLoadFormController.getChildren().add(lode);
    }

    public void btnUpdateMedicineOnAction(ActionEvent actionEvent) {
    }
}
