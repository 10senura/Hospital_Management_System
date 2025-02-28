package controller.doctor;

import com.jfoenix.controls.JFXComboBox;
import dto.Doctor;
import dto.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.DoctorService;
import service.custom.PatientService;
import service.custom.impl.doctorServiceImpl;
import service.custom.impl.patientServiceImpl;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DoctorRegisterFormController implements Initializable {
    public AnchorPane lodeFormController;
    public AnchorPane DoctorlodeFormController;
    public TextField txtDoctorQualification;
    public TextField txtContact;
    public TextField txtAvilability;
    public JFXComboBox <String> cmdDoctor;
    public TextField txtDoctorSepcialty;
    public TextField txtDoctorName;
    private final DoctorService doctorService = ServiceFactory.getInstance().getService(ServiceType.DOCTOR);

    public void btnBackDoctorViewOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/doctor_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.DoctorlodeFormController.getChildren().clear();
        this.DoctorlodeFormController.getChildren().add(lode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDoctorIds();
        cmdDoctor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                search(newValue);
            }
        });
    }

    public void btnDoctorRegisterOnAction(ActionEvent actionEvent) {
        Doctor doctor = new Doctor(
                1,
                txtDoctorName.getText(),
                txtDoctorSepcialty.getText(),
                txtDoctorQualification.getText(),
                txtAvilability.getText(),
                txtContact.getText()
        );

        if(doctorService.addDoctor(doctor)){
            new Alert(Alert.AlertType.INFORMATION,"Added Successfully!");
        }else{
            new Alert(Alert.AlertType.ERROR,"Added Not Successfully ?");
        }
    }

    public void btnUpdateDoctorOnAction(ActionEvent actionEvent) {
        try {
            if (cmdDoctor.getValue() == null) {
                new Alert(Alert.AlertType.WARNING, "Please select a doctor to update!").show();
                return;
            }

            int id;
            try {
                id = Integer.parseInt(cmdDoctor.getValue());
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid Doctor ID! Please select a valid doctor.").show();
                return;
            }

            if (
                    txtDoctorName.getText().isEmpty() ||
                            txtDoctorSepcialty.getText().isEmpty() ||
                            txtDoctorQualification.getText().isEmpty() ||
                            txtAvilability.getText().isEmpty() ||
                            txtContact.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill in all fields before updating!").show();
                return;
            }

            Doctor doctor = new Doctor(
                    id,
                    txtDoctorName.getText(),
                    txtDoctorSepcialty.getText(),
                    txtDoctorQualification.getText(),
                    txtAvilability.getText(),
                    txtContact.getText()
            );

            if (doctorService.updateDoctor(doctor)) {
                new Alert(Alert.AlertType.INFORMATION, "Doctor updated successfully!").show();
                loadDoctorIds();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed! Please try again.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }


    private void search(String id){
        Doctor doctor = doctorServiceImpl.getInstance().getDoctorBYId(Integer.parseInt(id));
        if (doctor != null) {
            txtDoctorName.setText(doctor.getName());
            txtDoctorSepcialty.setText(doctor.getSpecialty());
            txtDoctorQualification.setText(doctor.getQualifications());
            txtAvilability.setText(doctor.getAvailability());
            txtContact.setText(doctor.getContact_details());
        } else {
            new Alert(Alert.AlertType.WARNING, "Doctor not found!").show();
        }
    }

    public void loadDoctorIds(){
        ObservableList<String> doctorIds = FXCollections.observableArrayList();
        List<Doctor> doctors = Optional.ofNullable(doctorService.getAll()).orElse(Collections.emptyList());
        for (Doctor doctor : doctors) {
            doctorIds.add(String.valueOf(doctor.getDoctor_id()));
        }
        cmdDoctor.setItems(doctorIds);
    }
}
