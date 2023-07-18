package DBIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int givenVillainId = Integer.parseInt(scanner.nextLine());

        Connection connection = ConnectionWithServer.getSqlConnection();

        PreparedStatement getVillainNameStatement = connection.prepareStatement(
                "SELECT name from villains WHERE id = ?;");
        
        getVillainNameStatement.setInt(1, givenVillainId);

        ResultSet villainNameResultSet = getVillainNameStatement.executeQuery();

        if (!villainNameResultSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.", givenVillainId);
        } else {
            String villainName = villainNameResultSet.getString("name");

            System.out.printf("Villain: %s\n", villainName);

            PreparedStatement getAllMinionsByVillainIdStatement = connection.prepareStatement(
                    "SELECT m.name, m.age FROM villains AS v\n" +
                            "JOIN minions_villains mv on v.id = mv.villain_id\n" +
                            "JOIN minions m on m.id = mv.minion_id\n" +
                            "WHERE v.id = ?;");

            getAllMinionsByVillainIdStatement.setInt(1, givenVillainId);
            ResultSet minionsResultSet = getAllMinionsByVillainIdStatement.executeQuery();

            int count = 1;

            while (minionsResultSet.next()) {
                System.out.println(count + ". " + minionsResultSet.getString("name") +
                        " " + minionsResultSet.getInt("age"));

                count++;
            }
        }

        connection.close();

    }
}
