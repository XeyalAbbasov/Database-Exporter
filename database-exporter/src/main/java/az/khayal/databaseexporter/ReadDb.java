package az.khayal.databaseexporter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadDb {

    public List<String> readDbNames(String username, String pass) {
        List<String> dbNames = new ArrayList<String>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", username, pass);

            Statement stmt = conn.createStatement();
            ResultSet resultset = stmt.executeQuery("SHOW DATABASES;");

            if (stmt.execute("SHOW DATABASES;")) {
                resultset = stmt.getResultSet();
            }

            while (resultset.next()) {
                dbNames.add(resultset.getString("Database"));
            }

            resultset.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {
        }

        return dbNames;
    }
}
