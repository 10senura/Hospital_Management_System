package controller.medicine;

import com.jfoenix.controls.JFXComboBox;
import dto.Medicine;
import dto.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.MedicineService;
import service.custom.impl.medicineServiceImpl;
import service.custom.impl.patientServiceImpl;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class MedicineFormController implements Initializable {
    public AnchorPane medicineLoadFormController;
    public AnchorPane lodeFormController;
    public JFXComboBox <String> cmbmedicineId;
    public TextField txtmedicineName;
    public TextField txtM_Category;
    public TextField txtM_price;
    public TextField txtM_stock;
    public TextField txtM_ExpireDate;
    public TableView<Medicine> tblMedicineView;

    private final MedicineService medicineService = ServiceFactory.getInstance().getService(ServiceType.MEDICINE);
    List<Medicine> medicineList = new ArrayList<>();

    public void btnMedicineSubmitOnAction(ActionEvent actionEvent) {
        try {
            String name = txtmedicineName.getText();
            String category = txtM_Category.getText();
            double price = Double.parseDouble(txtM_price.getText());
            int stock = Integer.parseInt(txtM_stock.getText());
            LocalDate expireDate = LocalDate.parse(txtM_ExpireDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Medicine medicine = new Medicine(1, name, category, price, stock, expireDate);

            if (medicineService.addMedicine(medicine)) {
                new Alert(Alert.AlertType.INFORMATION, "Added Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Addition Failed!").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Number Format!").show();
        } catch (DateTimeParseException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Date Format! Use yyyy-MM-dd").show();
        }
    }
    public void btnBackAddMedicinePageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/medicine_form_view_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.medicineLoadFormController.getChildren().clear();
        this.medicineLoadFormController.getChildren().add(lode);
    }

    public void btnUpdateMedicineOnAction(ActionEvent actionEvent) {
        try {
            if (cmbmedicineId.getValue() == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a medicine to update!").show();
                return;
            }

            int id;
            try {
                id = Integer.parseInt(cmbmedicineId.getValue());
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid Medicine ID! Please select a valid medicine.").show();
                return;
            }

            if (
                    txtmedicineName.getText().isEmpty() ||
                            txtM_Category.getText().isEmpty() ||
                            txtM_price.getText().isEmpty() ||
                            txtM_stock.getText().isEmpty() ||
                            txtM_ExpireDate.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill in all fields before updating!").show();
                return;
            }

            double price;
            int stock;
            try {
                price = Double.parseDouble(txtM_price.getText());
                stock = Integer.parseInt(txtM_stock.getText());
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid price or stock format! Please enter valid numbers.").show();
                return;
            }

            LocalDate expireDate;
            try {
                expireDate = LocalDate.parse(txtM_ExpireDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid date format! Please use yyyy-MM-dd.").show();
                return;
            }

            Medicine medicine = new Medicine(
                    id,
                    txtmedicineName.getText(),
                    txtM_Category.getText(),
                    price,
                    stock,
                    expireDate
            );

            if (medicineService.updateMedicine(medicine)) {
                new Alert(Alert.AlertType.INFORMATION, "Medicine updated successfully!").show();
                loadMedicineTable();
                loadMedicineIds();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed! Please try again.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    private void loadMedicineTable() {
        medicineList.clear();
        medicineList.addAll(medicineService.getMedicine());
        tblMedicineView.setItems(FXCollections.observableList(medicineList));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMedicineIds();
        cmbmedicineId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                search(newValue);
            }
        });
    }

    private void search(String id){
        Medicine medicine = medicineServiceImpl.getInstance().getSearchMedicine(Integer.parseInt(id));
        if (medicine != null) {
            txtmedicineName.setText(medicine.getMedicine_name());
            txtM_Category.setText(medicine.getCategory());
            txtM_price.setText(String.valueOf(medicine.getPrice()));
            txtM_stock.setText(String.valueOf(medicine.getStock_quantity()));
            txtM_ExpireDate.setText(String.valueOf(medicine.getExpiry_date()));
        } else {
            new Alert(Alert.AlertType.WARNING, "Patient not found!").show();
        }
    }
    public void loadMedicineIds(){
        ObservableList<String> medicineIds = FXCollections.observableArrayList();
        List<Medicine> medicines = Optional.ofNullable(medicineService.getAll()).orElse(Collections.emptyList());
        for (Medicine medicine : medicines) {
            medicineIds.add(String.valueOf(medicine.getMedicine_id()));
        }
        cmbmedicineId.setItems(medicineIds);
    }


}
