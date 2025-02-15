package controller.register;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterPageFormController {
    public PasswordField txtConformePassword;
    public TextField txtName;
    public PasswordField txtPassword;
    public TextField txtEmail;

    public void btnSignUpFormOnAction(ActionEvent actionEvent) throws SQLException {
        String key ="12345";
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        if (txtPassword.getText().equals(txtConformePassword.getText())){
            System.out.println(true);
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email=" + "'" + txtEmail.getText() + "'");
            if (!resultSet.next()){
                System.out.println(false);

                String SQL = "INSERT INTO users (username,email,password) VALUES (?,?,?)";
                PreparedStatement psTm = connection.prepareStatement(SQL);
                psTm.setString(1,txtName.getText());
                psTm.setString(2,txtEmail.getText());
                psTm.setString(3,basicTextEncryptor.encrypt(txtPassword.getText()));
                psTm.executeUpdate();
            }else{
                System.out.println(true);
            }
        }else {
            System.out.println(false);
        }
    }
    public void btnLoginFormOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_page_form.fxml"))));
            stage.show();
            ((Stage) txtEmail.getScene().getWindow()).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}