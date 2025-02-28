package controller.Appointment;

import dto.Appointment;
import dto.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import repository.custom.AppointmentDao;
import service.ServiceFactory;
import service.custom.AppointmentService;
import service.custom.DoctorService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentFormController implements Initializable {
    public AnchorPane lodeFormControllerAppoment;
    public TableView tblAppoimementView;
    public TableColumn clmappointment_id;
    public TableColumn clmpatient_id;
    public TableColumn clmdoctor_id;
    public TableColumn clmappointment_time;
    public TableColumn clmappointment_date;
    public TableColumn clmAppoinementDeleteButton;
    private final AppointmentService appointmentService = ServiceFactory.getInstance().getService(ServiceType.APPOINTMENT);
    List<Appointment> appointmentList = new ArrayList<>();

    public void btnAppointmentAddPageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/appointment_view_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormControllerAppoment.getChildren().clear();
        this.lodeFormControllerAppoment.getChildren().add(lode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmappointment_id.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        clmpatient_id.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        clmdoctor_id.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        clmappointment_date.setCellValueFactory(new PropertyValueFactory<>("appointment_date"));
        clmappointment_time.setCellValueFactory(new PropertyValueFactory<>("appointment_time"));

        clmAppoinementDeleteButton.setCellFactory(param -> new TableCell<Appointment, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #3497f9; -fx-text-fill: white;");
                deleteButton.setOnMouseEntered(e -> deleteButton.setStyle("-fx-background-color: #ff1e00; -fx-text-fill: white;"));
                deleteButton.setOnMouseExited(e -> deleteButton.setStyle("-fx-background-color: #3497f9; -fx-text-fill: white;"));

                deleteButton.setOnAction(event -> {
                    Appointment appointment = getTableRow().getItem();
                    if (appointment != null) {
                        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are you sure you want to delete this patient?",
                                ButtonType.YES, ButtonType.NO);
                        confirmDialog.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.YES) {
                                boolean success = appointmentService.deleteAppointment(String.valueOf(appointment.getAppointment_id()));
                                if (success) {
                                    getTableView().getItems().remove(appointment);
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
        tblAppoimementView.getItems().clear();
        List<Appointment> appointments = appointmentService.getAll();
        if (appointments != null) {
            ObservableList<Appointment> observableList = FXCollections.observableArrayList(appointments);
            tblAppoimementView.setItems(observableList);
        } else {
            new Alert(Alert.AlertType.WARNING, "No patient records found.").show();
        }
    }
}
