package controller.medicine;

import dto.Medicine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.MedicineService;
import service.custom.PatientService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MedicineFormViewController implements Initializable {
    public AnchorPane lodeFormController;
    public TextField txtSearchmedicine;
    public TableView <Medicine> tblMedicineView;
    public TableColumn clmMedicineId;
    public TableColumn clmMedicineName;
    public TableColumn clmMedicineCategory;
    public TableColumn clmMedicineStock;
    public TableColumn clmMedicinePrice;
    public TableColumn clmMedicineExpireDate;
    public AnchorPane MrdicineloardFormController;
    public TableColumn clmMidicineDeleteButton;
    private final MedicineService medicineService = ServiceFactory.getInstance().getService(ServiceType.MEDICINE);


    public void btnAddmedicineOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/medicine_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.MrdicineloardFormController.getChildren().clear();
        this.MrdicineloardFormController.getChildren().add(lode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clmMedicineId.setCellValueFactory(new PropertyValueFactory<>("medicine_id"));
        clmMedicineName.setCellValueFactory(new PropertyValueFactory<>("medicine_name"));
        clmMedicineCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        clmMedicinePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmMedicineStock.setCellValueFactory(new PropertyValueFactory<>("stock_quantity"));
        clmMedicineExpireDate.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));

        clmMidicineDeleteButton.setCellFactory(param -> new TableCell<Medicine, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #3497f9; -fx-text-fill: FFFFFF;");
                deleteButton.setOnMouseEntered(e -> deleteButton.setStyle("-fx-background-color: #ff1e00; -fx-text-fill: FFFFFF;"));
                deleteButton.setOnMouseExited(e -> deleteButton.setStyle("-fx-background-color: #3497f9; -fx-text-fill: FFFFFF;"));

                deleteButton.setOnAction(event -> {
                    Medicine medicine = getTableRow().getItem();
                    if (medicine != null) {
                        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are you sure you want to delete this Medicine?",
                                ButtonType.YES, ButtonType.NO);
                        confirmDialog.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.YES) {
                                boolean success = medicineService.deleteMedicine(String.valueOf(medicine.getMedicine_id()));
                                if (success) {
                                    getTableView().getItems().remove(medicine);
                                    new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully!").show();
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Delete Failed!").show();
                                }
                            }
                        });
                    }
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        loadTable();
    }

    private void loadTable() {
        tblMedicineView.getItems().clear();
        List<Medicine> medicines = medicineService.getAll();
        if (medicines != null) {
            ObservableList<Medicine> observableList = FXCollections.observableArrayList(medicines);
            tblMedicineView.setItems(observableList);
        } else {
            new Alert(Alert.AlertType.WARNING, "No Medicine records found.").show();
        }
    }

}
