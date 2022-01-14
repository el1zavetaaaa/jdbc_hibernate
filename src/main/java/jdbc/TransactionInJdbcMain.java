package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static jdbc.util.UtilForJdbcClasses.*;

public class TransactionInJdbcMain {
    public static void main(String[] args) throws SQLException {
        Properties properties = loadProperties();
        String url = properties.getProperty("url");

        try (Connection connection = DriverManager.getConnection(url, properties)) {
            connection.setAutoCommit(false);

            System.out.print("Fruits before transaction is commited: ");
            showFruits();

            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into fruits(id,name) " +
                    "values(default,'lemon')");
            statement.executeUpdate("update fruits set name='grape'" +
                    "where id=2");

            boolean ok = askUserIfOkToSave();

            if (ok) {
                connection.commit();
                System.out.println("Transaction was commited successfully!");
            } else {
                connection.rollback();
                System.out.println("Transaction was rejected!");
            }

            System.out.print("Fruits after transactions are made:");
            showFruits();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
