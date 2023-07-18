package DBIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = ConnectionWithServer.getSqlConnection();

        String country = scanner.nextLine();

        PreparedStatement changeToUpperTownsStatement = connection.prepareStatement(
                "SELECT UPPER(name) AS town FROM towns WHERE country = ?;");

        changeToUpperTownsStatement.setString(1, country);
        ResultSet upperCaseTownsResultSet = changeToUpperTownsStatement.executeQuery();

        List<String> townsList = new ArrayList<>();

        while (upperCaseTownsResultSet.next()) {
            townsList.add(upperCaseTownsResultSet.getString("town"));
        }

        if (townsList.isEmpty()) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.\n", townsList.size());
            System.out.println(townsList);
        }

        connection.close();

    }
}
