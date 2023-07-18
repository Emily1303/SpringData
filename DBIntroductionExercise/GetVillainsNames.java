package DBIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainsNames {

    public static void main(String[] args) throws SQLException {

        Connection connection = ConnectionWithServer.getSqlConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT `name`, COUNT(DISTINCT minion_id) AS count_minions FROM villains AS v\n" +
                        "                        JOIN minions_villains AS mv ON v.id = mv.villain_id\n" +
                        "                        GROUP BY `name`\n" +
                        "                        HAVING count_minions > 15\n" +
                        "                        ORDER BY count_minions DESC;");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " +
                    resultSet.getInt("count_minions"));
        }

        connection.close();

    }
}
