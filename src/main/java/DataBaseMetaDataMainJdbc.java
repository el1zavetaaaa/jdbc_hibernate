import java.sql.*;
import java.util.Properties;

import static util.UtilForJdbcClasses.loadProperties;


public class DataBaseMetaDataMainJdbc {
    public static void main(String[] args) throws SQLException {

        String catalog = "Schemas";
        String schemaPattern = "public";
        String tableNamePattern = "fruits";
        String columnNamePattern = null;
        String[] types = null;
        Properties properties = loadProperties();
        String url = properties.getProperty("url");

        ResultSet resultSet = null;

        try (Connection connection =
                     DriverManager.getConnection(url, properties)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            System.out.println("Product name: "
                    + databaseMetaData.getDatabaseProductName());

            System.out.println("Product version: "
                    + databaseMetaData.getDatabaseProductVersion());

            System.out.println("\nJDBC Driver name: "
                    + databaseMetaData.getDriverName());
            System.out.println("JDBC Driver version: "
                    + databaseMetaData.getDriverVersion());
            System.out.println("\nList of tables"
                    + "\n---------------");

            resultSet = databaseMetaData.getTables(catalog,
                    schemaPattern, tableNamePattern, types);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("TABLE_NAME"));
            }
            System.out.println("\nList of columns"
                    + "\n---------------");
            resultSet = databaseMetaData.getColumns(catalog, schemaPattern
                    , "fruits", columnNamePattern);

            while (resultSet.next()) {
                columnNamePattern = resultSet.getString("COLUMN_NAME");
                System.out.println(columnNamePattern);
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet1 = statement
                    .executeQuery("select id,name from fruits");

            ResultSetMetaData resultSetMetaData = resultSet1.getMetaData();

            int columnCount = resultSetMetaData.getColumnCount();
            System.out.println("\nColumn count is: " + columnCount);

            for (int column = 1; column <= columnCount; column++) {
                System.out.println("\nColumn name: "
                        + resultSetMetaData.getColumnName(column));
                System.out.println("Column type name: "
                        + resultSetMetaData.getColumnTypeName(column));
                System.out.println("Is Nullable: "
                        + resultSetMetaData.isNullable(column));
                System.out.println("Is Auto Incremenet: "
                        + resultSetMetaData.isAutoIncrement(column));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

