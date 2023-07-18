package DBIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseMinionsAge {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = ConnectionWithServer.getSqlConnection();

        String[] idsArray = scanner.nextLine().split(" ");
        for (int i = 0; i < idsArray.length; i++) {
            int currentId = Integer.parseInt(idsArray[i]);

            PreparedStatement changeMinionStatement = connection.prepareStatement(
                    "UPDATE minions\n" +
                            "SET age = age + 1,\n" +
                            "    name = LOWER(name)\n" +
                            "WHERE id = ?;");

            changeMinionStatement.setInt(1, currentId);
            changeMinionStatement.executeUpdate();
        }

        PreparedStatement getAllMinionsStatement = connection.prepareStatement(
                "SELECT name, age FROM minions;", ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet allMinionsResultSet = getAllMinionsStatement.executeQuery();

        while (allMinionsResultSet.next()) {
            System.out.println(allMinionsResultSet.getString("name") +
                    " " + allMinionsResultSet.getInt("age"));
        }

        connection.close();

    }
}
