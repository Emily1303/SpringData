package DBIntroductionLab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class SimpleJavaApplication {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/soft_uni", props);

        System.out.println("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT first_name, last_name FROM employees \n" +
                        "WHERE salary > ?;");

        preparedStatement.setDouble(1, salary);

        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            System.out.println(result.getString("first_name") + " " + result.getString("last_name"));
        }
    }
}
