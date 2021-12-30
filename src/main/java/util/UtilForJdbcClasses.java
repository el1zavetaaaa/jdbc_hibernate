package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class UtilForJdbcClasses {
    public static Properties loadProperties() {

        Properties props = new Properties();

        try (InputStream input = UtilForJdbcClasses.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }

    public static boolean askUserIfOkToSave() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nTransaction is ready" +
                "\nIs it okay to save? yes/no:");
        String getAnswer = scanner.nextLine();

        if (getAnswer.equals("yes")) {
            boolean flag = true;
            return flag;
        } else {
            boolean flag = false;
            return flag;
        }
    }


    public static void showFruits() {
        Properties properties = loadProperties();
        String url = properties.getProperty("url");

        try (Connection connection = DriverManager.getConnection(url, properties)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select*from fruits");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getString("name") + " ");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
