package controller.Appointment;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AppointmentFormController {
    public AnchorPane lodeFormControllerAppoment;
    public AnchorPane lodeFormController1;
    public AnchorPane lodeFormControllera;
    public AnchorPane lodeFormController;
    public TableView tblAppoimementView;
    public TableColumn clmappointment_id;
    public TableColumn clmpatient_id;
    public TableColumn clmdoctor_id;
    public TableColumn clmappointment_time;
    public TableColumn clmappointment_date;

    public void btnAppointmentAddPageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/View/appointment_view_form_controller.fxml");

        assert resource!=null;
        Parent lode = FXMLLoader.load(resource);
        this.lodeFormControllerAppoment.getChildren().clear();
        this.lodeFormControllerAppoment.getChildren().add(lode);
    }

}
