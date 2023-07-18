package DBIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = ConnectionWithServer.getSqlConnection();

        int idVillain = Integer.parseInt(scanner.nextLine());

        PreparedStatement getVillainNameStatement = connection.prepareStatement(
                "SELECT name FROM villains WHERE id = ?;");

        getVillainNameStatement.setInt(1, idVillain);
        ResultSet villainNameResultSet = getVillainNameStatement.executeQuery();

        String villainName = null;
        while (villainNameResultSet.next()) {
            villainName = villainNameResultSet.getString("name");
        }

        if (villainName == null) {
            System.out.println("No such villain was found");
            return;
        }

        PreparedStatement getMinionsCountVillainStatement = connection.prepareStatement(
                "SELECT COUNT(*) FROM minions_villains WHERE villain_id = ?;");

        getMinionsCountVillainStatement.setInt(1, idVillain);
        ResultSet countMinionsResultSet = getMinionsCountVillainStatement.executeQuery();

        int countMinions = 0;
        while (countMinionsResultSet.next()) {
            countMinions = countMinionsResultSet.getInt(1);
        }

        connection.setAutoCommit(false);

        try {
            PreparedStatement releaseMinionsStatement = connection.prepareStatement(
                    "DELETE FROM minions_villains WHERE villain_id = ?;");

            releaseMinionsStatement.setInt(1, idVillain);
            releaseMinionsStatement.executeUpdate();

            PreparedStatement deleteVillainStatement = connection.prepareStatement(
                    "DELETE FROM villains WHERE id = ?;");

            deleteVillainStatement.setInt(1, idVillain);
            deleteVillainStatement.executeUpdate();

            connection.commit();
        } catch (SQLException sqlException) {
            connection.rollback();
        }

        System.out.printf("%s was deleted\n", villainName);
        System.out.printf("%d minions released", countMinions);

        connection.close();

    }
}
