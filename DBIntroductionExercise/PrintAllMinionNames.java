package DBIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionNames {

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionWithServer.getSqlConnection();

        PreparedStatement getAllMinionNamesStatement = connection.prepareStatement(
                "SELECT name FROM minions;", ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet minionNamesResultSet = getAllMinionNamesStatement.executeQuery();

        List<String> namesList = new ArrayList<>();

        while (minionNamesResultSet.next()) {
            namesList.add(minionNamesResultSet.getString("name"));
        }

        int count = 0;

        for (int i = 0; i < namesList.size(); i++) {
            int last = namesList.size() - 1;
            int first = 0;
            if (i % 2 == 0) {
                System.out.println(namesList.get(first + count));
            } else {
                System.out.println(namesList.get(last - count));

                count++;
            }
        }

        connection.close();

    }
}
