package db;
import java.sql.*;
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

    private int getCount(Connection connection, String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
