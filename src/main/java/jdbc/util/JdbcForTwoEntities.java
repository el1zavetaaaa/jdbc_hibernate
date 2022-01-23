package jdbc.util;

import jdbc.entity.Contact;
import jdbc.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static jdbc.util.UtilForJdbcClasses.loadProperties;

public class JdbcForTwoEntities {
    static Properties properties = loadProperties();
    static String url = properties.getProperty("url");

    private static final String INSERT = "insert into " +
            "contacts(contact_name,customer_id) " +
            "values(?,?)";

    private static final String UPDATE = "update contacts " +
            "set customer_id=? " +
            "where contact_id=? ";

    private static final String GET_CONTACT = "select from contacts where contact_id=?";

    public static void main(String[] args) throws SQLException {

        getContact(1);

        try (Connection connection
                     = DriverManager.getConnection(url, properties)) {
            List<Customer> customers = new ArrayList<>();
            customers.add(new Customer(1, "customer 1"));

            List<Contact> contacts = new ArrayList<>();

            for (Customer customer : customers) {
                int id = customer.getId();
                Contact contact = getContact(2);
                contact.setCustomer_id(id);
                contacts.add(contact);
            }

            updateAll(contacts);
        }
    }

    public static void saveAll(List<Contact> contacts) throws SQLException {
        try (Connection connection
                     = DriverManager.getConnection(url, properties);
             PreparedStatement statement
                     = connection.prepareStatement(INSERT)) {
            for (Contact contact : contacts) {
                statement.setString(1, contact.getName());
                statement.setInt(2, contact.getCustomer_id());
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    public static void updateAll(List<Contact> contacts) throws SQLException {
        try (Connection connection
                     = DriverManager.getConnection(url, properties);
             PreparedStatement statement
                     = connection.prepareStatement(UPDATE)) {
            for (Contact contact : contacts) {
                statement.setInt(1, contact.getCustomer_id());
                statement.setInt(2, contact.getId());
                statement.executeUpdate();
            }
        }
    }

    public static Contact getContact(int id) throws SQLException {
        Contact contact = null;
        try (Connection connection
                     = DriverManager.getConnection(url, properties);
             PreparedStatement statement
                     = connection.prepareStatement(GET_CONTACT)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                contact = new Contact(id);
                System.out.println(statement);
            }
        }
        return contact;
    }


}
