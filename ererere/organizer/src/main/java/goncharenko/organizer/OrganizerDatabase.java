package goncharenko.organizer;

import java.sql.*;

public class OrganizerDatabase {
    private Connection connection = null;
    private Statement queryStatement;
    private boolean isConnectionEstablished = false;

    public OrganizerDatabase() {
    }


    public void establishConnection() {
        try {
            if (!isConnectionEstablished) {
                Class.forName("org.sqlite.JDBC"); //sterowniki

                connection = DriverManager.getConnection("jdbc:sqlite:db/organizer-db.db");

                queryStatement = connection.createStatement();
                isConnectionEstablished = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeSelectQuery(String queryString) {
        ResultSet queryResult = null;
        try {
            queryResult = queryStatement.executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryResult;
    }

    public void executeUpdateQuery(String queryString) {
        try {
            queryStatement.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeDeleteQuery(String queryString) {
        try {
            queryStatement.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeInsertQuery(String queryString) {
        try {
            queryStatement.executeUpdate(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
