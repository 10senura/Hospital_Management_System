package Controller.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBConnection {

    private static DBConnection instance;

    private Connection PatientList;

    private DBConnection() throws SQLException {

        this.PatientList= DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalmanagementsystem","root","1234" );
    }

    public Connection getConnection(){
        return PatientList;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance==null){
            return instance= new DBConnection();
        }
        return instance;
    }
}
