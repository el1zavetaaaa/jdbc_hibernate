import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.Properties;

import static util.UtilForJdbcClasses.loadProperties;

public class JdbcMain {
    public static void main(String[] args) throws SQLException {
        Properties properties = loadProperties();
        String url = properties.getProperty("url");

        try (Connection connection = DriverManager.getConnection(url, properties)) {

            // 1. Get connection to database.
            System.out.println("Database connection successful!\n");

            // 2. Create a preparedStatement.
            PreparedStatement preparedStatement = connection.prepareStatement("delete from fruits where name=? ");
            preparedStatement.setString(1, "grape");

            Statement statement = connection.createStatement();

//            // 3.Add a new fruit to database.
//            int rowsAffected = preparedStatement.executeUpdate("insert into fruits" +
//                    "(id,name)" + "values"+ "(default,'pineapple')");

            // 4.Execute SQL query.
            ResultSet resultSet = statement.executeQuery("select * from fruits");


//            // 5.Process the resultSet.
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

//            // 6. Update an information in the database.
//
//            int rowsUpdated = preparedStatement.executeUpdate(
//                    "update fruits "+
//                    " set name ='grape' " +
//                    " where id=3");
//            // 7.Delete an information from the database.
//
//            int rowsDeleted = preparedStatement.executeUpdate(
//                    "delete from fruits"+
//                         " where name='plum' and id=5"
//            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}