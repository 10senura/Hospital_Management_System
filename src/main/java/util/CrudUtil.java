package util;

import Controller.DB.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String SQL, Object... val) throws SQLException {

        //   new ArrayList<T>();  ==  <T>T

        PreparedStatement psTm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        for (int i = 0; i < val.length; i++) {
            psTm.setObject(i + 1, val[i]);
        }
        if (SQL.startsWith("SELECT") || SQL.startsWith("select")) {
            return (T) psTm.executeQuery();
        } else {

            return (T) (Boolean) (psTm.executeUpdate() > 0);
        }
    }
}
