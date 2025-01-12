package Controller;

import Controller.DB.DBConnection;
import Controller.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class LoginPageFormController {

    public TextField txtEmail;
    public PasswordField txtPassword;

    public void btnLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        String key = "12345";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        String SQL = "SELECT * FROM users WHERE email='" + txtEmail.getText() + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);

            if (resultSet.next()) {
                Users user = new Users(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );

                if (user != null) {
                    if (basicTextEncryptor.decrypt(user.getPassword()).equals(txtPassword.getText())) {
                        System.out.println("Login!");

                        // Open Dashboard
                        Stage stage = new Stage();
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dash_board_form_comtroller.fxml"))));
                        stage.show();
                        ((Stage) txtEmail.getScene().getWindow()).close();
                    } else {
                        showCustomPopup("Login Failed", "The password you entered is incorrect. Please try again.");
                    }
                } else {
                    showCustomPopup("Login Failed", "No user found with the given email. Please check and try again.");
                }
            } else {
                showCustomPopup("Login Failed", "No user found with the given email. Please check and try again.");
            }
        } catch (SQLException e) {
            showCustomPopup("Database Error", e.getMessage());
        }
    }

    private void showCustomPopup(String title, String message) {
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane, 400, 150);
        Stage stage = new Stage();
        stage.setScene(scene);

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(title));
        content.setBody(new Text(message));

        JFXButton okButton = new JFXButton("OK");
        okButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 10 20; -fx-font-size: 14px;");
        okButton.setOnAction(event -> stage.close());
        content.setActions(okButton);
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        dialog.show();

        stage.showAndWait();
    }

    public void btnSingUpOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/register_page_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
