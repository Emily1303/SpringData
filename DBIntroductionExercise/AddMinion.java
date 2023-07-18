package DBIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMinion {

    private static final String EVILNESS_FACTOR = "evil";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionWithServer.getSqlConnection();

        Scanner scanner = new Scanner(System.in);

        String givenMinion = scanner.nextLine();
        String givenVillain = scanner.nextLine();

        String[] minionsArr = givenMinion.split(" ");
        String givenMinionName = minionsArr[1];
        int givenMinionAge = Integer.parseInt(minionsArr[2]);
        String givenMinionTown = minionsArr[3];

        String[] villainArr = givenVillain.split(" ");
        String givenVillainName = villainArr[1];

        PreparedStatement getTownIdStatement = connection.prepareStatement(
                "SELECT id FROM towns WHERE name = ?;");

        getTownIdStatement.setString(1, givenMinionTown);

        ResultSet townIdResultSet = getTownIdStatement.executeQuery();

        int townId = 0;

        if (!townIdResultSet.next()) {
            PreparedStatement insertTownStatement = connection.prepareStatement(
                    "INSERT INTO towns (name) VALUES (?);");

            insertTownStatement.setString(1, givenMinionTown);
            insertTownStatement.executeUpdate();

            getTownIdStatement.setString(1, givenMinionTown);
            ResultSet newTownIdResultSet = getTownIdStatement.executeQuery();

            while (newTownIdResultSet.next()) {
                townId = newTownIdResultSet.getInt("id");
                System.out.printf("Town %s was added to the database.\n", givenMinionTown);
            }
        } else {
            townId = townIdResultSet.getInt("id");
        }

        PreparedStatement getVillainIdStatement = connection.prepareStatement(
                "SELECT id FROM villains WHERE name = ?;");

        getVillainIdStatement.setString(1, givenVillainName);
        ResultSet villainIdResultSet = getVillainIdStatement.executeQuery();

        int villainId = 0;
        if (!villainIdResultSet.next()) {
            PreparedStatement insertVillainStatement = connection.prepareStatement(
                    "INSERT INTO villains (name, evilness_factor) VALUES (?, ?);");

            insertVillainStatement.setString(1, givenVillainName);
            insertVillainStatement.setString(2, EVILNESS_FACTOR);
            insertVillainStatement.executeUpdate();

            getVillainIdStatement.setString(1, givenVillainName);
            ResultSet newVillainIdResultSet = getVillainIdStatement.executeQuery();

            while (newVillainIdResultSet.next()) {
                villainId = newVillainIdResultSet.getInt("id");
                System.out.printf("Villain %s was added to the database.\n", givenVillainName);
            }
        } else {
            villainId = villainIdResultSet.getInt("id");
        }

        PreparedStatement insertNewMinionStatement = connection.prepareStatement(
                "INSERT INTO minions (name, age, town_id) VALUES (?, ?, ?);");

        insertNewMinionStatement.setString(1, givenMinionName);
        insertNewMinionStatement.setInt(2, givenMinionAge);
        insertNewMinionStatement.setInt(3, townId);

        insertNewMinionStatement.executeUpdate();

        PreparedStatement getNewMinionIdStatement = connection.prepareStatement(
                "SELECT id FROM minions WHERE name = ?;");

        getNewMinionIdStatement.setString(1, givenMinionName);
        ResultSet minionIdResultSet = getNewMinionIdStatement.executeQuery();

        int minionId = 0;
        while (minionIdResultSet.next()) {
            minionId = minionIdResultSet.getInt("id");
        }

        PreparedStatement insertMinionVillainStatement = connection.prepareStatement(
                "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?);");

        insertMinionVillainStatement.setInt(1, minionId);
        insertMinionVillainStatement.setInt(2, villainId);
        insertMinionVillainStatement.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.", givenMinionName, givenVillainName);

        connection.close();

    }
}
