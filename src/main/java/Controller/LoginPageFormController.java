package Controller;

import Controller.DB.DBConnection;
import Controller.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageFormController {

    public TextField txtEmail;
    public PasswordField txtPassword;

    public void btnLoginFormOnAction(ActionEvent actionEvent) throws  IOException {
        String key ="12345";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        String SQL = "SELECT * FROM users WHERE email=" + "'" + txtEmail.getText() + "'";
        try {
            Connection  connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);
            resultSet.next();
            Users user = new Users(resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            if (user != null) {
                if (basicTextEncryptor.decrypt(user.getPassword()).equals(txtPassword.getText())) {
                    System.out.println("Login!");
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dash_board_form_comtroller.fxml"))));
                    stage.show();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Check your Password").show();
                    System.out.println("Check your password");
                }
            } else {
                new Alert(Alert.AlertType.ERROR,"User Not Found").show();
                System.out.println("user Not found!");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
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
