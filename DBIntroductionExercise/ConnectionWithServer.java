package DBIntroductionExercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum ConnectionWithServer {

    ;

    public static Connection getSqlConnection() throws SQLException {
        Properties props = new Properties();

        props.setProperty("user", Constants.GET_USER);
        props.setProperty("password", Constants.GET_PASSWORD);

        return DriverManager.getConnection(
                Constants.GET_CONNECTION_WITH_DATABASE, props);
    }
}
