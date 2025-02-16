package controller.billing;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class BillingformController {

    @FXML
    private TextField billIdField;

    @FXML
    private TextField patientIdField;

    @FXML
    private TextField totalAmountField;

    @FXML
    private ComboBox<String> paymentStatusBox;

    @FXML
    private DatePicker generatedDatePicker;

    @FXML
    private void generateBill() {
        String billId = billIdField.getText();
        String patientId = patientIdField.getText();
        String totalAmount = totalAmountField.getText();
        String paymentStatus = paymentStatusBox.getValue();
        LocalDate generatedDate = generatedDatePicker.getValue();

        if (billId.isEmpty() || patientId.isEmpty() || totalAmount.isEmpty() || paymentStatus == null || generatedDate == null) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        // Display the generated bill info (this could be saved to a database instead)
        String billInfo = String.format(
                "Bill ID: %s\nPatient ID: %s\nTotal Amount: %s\nPayment Status: %s\nGenerated Date: %s",
                billId, patientId, totalAmount, paymentStatus, generatedDate
        );

        showAlert("Bill Generated", billInfo);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
