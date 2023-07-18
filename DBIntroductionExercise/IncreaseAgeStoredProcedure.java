package DBIntroductionExercise;

import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = ConnectionWithServer.getSqlConnection();

        int id = Integer.parseInt(scanner.nextLine());

        CallableStatement increaseMinionsAgeStatement = connection.prepareCall(
                "CALL usp_get_older(?);");

        increaseMinionsAgeStatement.setInt(1, id);
        increaseMinionsAgeStatement.execute();

        PreparedStatement getMinionStatement = connection.prepareStatement(
                "SELECT name, age FROM minions WHERE id = ?;");

        getMinionStatement.setInt(1, id);
        ResultSet minionResultSet = getMinionStatement.executeQuery();

        while (minionResultSet.next()) {
            System.out.println(minionResultSet.getString("name") +
                    " " + minionResultSet.getInt("age"));
        }

        connection.close();

    }
}
