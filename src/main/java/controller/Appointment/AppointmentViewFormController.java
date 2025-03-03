package controller.Appointment;

import com.jfoenix.controls.JFXComboBox;
import dto.Appointment;
import dto.Billing;
import dto.Doctor;
import dto.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.AppointmentService;
import service.custom.DoctorService;
import service.custom.PatientService;
import service.custom.impl.appointmentServiceImpl;
import service.custom.impl.billingServiceImpl;
import util.ServiceType;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class AppointmentViewFormController implements Initializable {
    public AnchorPane AppomentlodeFormController;
    public AnchorPane lodeFormController2;
    public AnchorPane lodeFormController1;
    public AnchorPane lodeFormController;
    public JFXComboBox <String> cmbDoctorId;
    public JFXComboBox <String> cmbPatientId;
    public JFXComboBox <String> cmbAppoinementId;
    public DatePicker txtDate;
    public TextField txtTime;

    private final AppointmentService appointmentService = ServiceFactory.getInstance().getService(ServiceType.APPOINTMENT);
    private final PatientService patientService = ServiceFactory.getInstance().getService(ServiceType.PATIENT);
    private final DoctorService doctorService = ServiceFactory.getInstance().getService(ServiceType.DOCTOR);


    public void btnPageBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/appointment_view_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormController.getChildren().clear();
        this.lodeFormController.getChildren().add(lode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAppointmentIds();
        loadPatientIds();
        loadDoctorIds();
        cmbAppoinementId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                search(newValue);
            }
        });
    }

    public void btnAppomentAddOnAction(ActionEvent actionEvent) {
        if (cmbPatientId.getValue() == null ||
                cmbDoctorId.getValue() == null ||
                txtDate.getValue() == null ||
                txtTime.getText().isEmpty())
        {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
            return;
        }

        try {
            LocalTime appointmentTime = LocalTime.parse(txtTime.getText(), DateTimeFormatter.ofPattern("HH:mm"));

            Appointment appointment = new Appointment(
                    1,
                    Integer.parseInt(cmbPatientId.getValue()),
                    Integer.parseInt(cmbDoctorId.getValue()),
                    txtDate.getValue(),
                    appointmentTime
            );

            boolean isSaved = appointmentServiceImpl.getInstance().addAppointment(appointment);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Appointment saved successfully!").show();
                loadAppointmentIds();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Appointment!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid time format! Use HH:mm (e.g., 14:30)").show();
        }
    }

    public void btnAppomentPageBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/appointment_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.AppomentlodeFormController.getChildren().clear();
        this.AppomentlodeFormController.getChildren().add(lode);
    }

    public void btnUpdateAppointmentOnAction(ActionEvent actionEvent) {
        if (cmbAppoinementId.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Select an Appointment to update!").show();
            return;
        }

        if (txtDate.getValue() == null || txtTime.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
            return;
        }

        try {
            LocalTime appointmentTime = LocalTime.parse(txtTime.getText(), DateTimeFormatter.ofPattern("HH:mm"));

            Appointment appointment = new Appointment(
                    Integer.parseInt(cmbAppoinementId.getValue()),
                    Integer.parseInt(cmbPatientId.getValue()),
                    Integer.parseInt(cmbDoctorId.getValue()),
                    txtDate.getValue(),
                    appointmentTime
            );

            boolean isUpdated = appointmentServiceImpl.getInstance().updateAppointment(appointment);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Appointment updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Appointment!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid time format! Use HH:mm (e.g., 14:30)").show();
        }
    }

    public void loadPatientIds() {
        ObservableList<String> patientIds = FXCollections.observableArrayList();
        List<Patient> patients = Optional.ofNullable(patientService.getAll()).orElse(Collections.emptyList());

        for (Patient patient : patients) {
            patientIds.add(String.valueOf(patient.getPatient_id()));
        }
        cmbPatientId.setItems(patientIds);
    }
    public void loadDoctorIds() {
        ObservableList<String> doctorIds = FXCollections.observableArrayList();
        List<Doctor> doctors = Optional.ofNullable(doctorService.getAll()).orElse(Collections.emptyList());

        for (Doctor doctor : doctors) {
            doctorIds.add(String.valueOf(doctor.getDoctor_id()));
        }
        cmbDoctorId.setItems(doctorIds);
    }

    private void search(String id) {
        if (id == null || id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter an appointment ID!").show();
            return;
        }
        try {
            int appointmentId = Integer.parseInt(id);
            Appointment appointment = appointmentServiceImpl.getInstance().getSearchAppointment(appointmentId);

            if (appointment != null) {
                if (appointment.getAppointment_date() != null) {
                    if (appointment.getAppointment_date() instanceof LocalDate) {
                        txtDate.setValue(appointment.getAppointment_date());
                    } else {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            txtDate.setValue(LocalDate.parse(appointment.getAppointment_date().toString(), formatter));
                        } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR, "Invalid date format!").show();
                        }
                    }
                } else {
                    txtDate.setValue(null);
                }

                cmbPatientId.setValue(String.valueOf(appointment.getPatient_id()));
                cmbDoctorId.setValue(String.valueOf(appointment.getDoctor_id()));
                txtTime.setText(String.valueOf(appointment.getAppointment_time()));

            } else {
                new Alert(Alert.AlertType.WARNING, "Appointment not found!").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID format! Please enter a number.").show();
        }
    }


    public void loadAppointmentIds(){
        ObservableList<String> appointmentids = FXCollections.observableArrayList();
        List<Appointment> appointments = Optional.ofNullable(appointmentService.getAll()).orElse(Collections.emptyList());
        for (Appointment appointment : appointments) {
            appointmentids.add(String.valueOf(appointment.getAppointment_id()));
        }
        cmbAppoinementId.setItems(appointmentids);
    }

}
