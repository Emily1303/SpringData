package DBIntroductionLab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class JavaApplicationDiablo {

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/diablo", props);

        System.out.print("Enter user: ");
        String enteredUser = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT first_name, last_name, COUNT(*) AS 'games' FROM users \n" +
                        "JOIN users_games AS ug \n" +
                        "ON users.id = ug.user_id \n" +
                        "WHERE user_name = ? \n" +
                        "GROUP BY first_name, last_name;");

        preparedStatement.setString(1, enteredUser);

        ResultSet result = preparedStatement.executeQuery();

        if (result.next()) {
            System.out.printf("User: %s\n" +
                    "%s %s has played %d games", enteredUser, result.getString("first_name"),
                    result.getString("last_name"), result.getInt("games"));
        } else {
            System.out.println("No such user exists");
        }
    }
}
