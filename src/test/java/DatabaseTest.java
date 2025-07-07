import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*; // Imports classes like Connection, Statement, and ResultSet to handle database connections, execute SQL queries, and process results, enabling interaction with database
import java.util.Properties; // Imports the Properties class to load and manage key-value pairs (URL, username, password)
import java.io.InputStream; // Imports InputStream to read the db.properties file from the classpath, allowing the code to access configuration data as a stream

/**
 * This class contains TestNG tests for validating database operations on the game_accounts database.
 */
public class DatabaseTest {

    private Connection connection;

    @BeforeClass
    // Sets up database connection by loading db.properties and initializing JDBC
    public void setup() throws Exception {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(input);
        }

        // Establishes a connection to the database using the URL, username, and password from properties
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"));
        System.out.println("Connected to database");
    }

    @AfterClass
    // Closes the database connection to prevent resource leaks after all tests are completed
    public void teardown() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed");
        }
    }

    @Test
    // Tests the total number of records in the accounts table, expecting exactly 25 entries
    public void testRecordCount() throws Exception {
        String sql = "SELECT COUNT(*) FROM accounts";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            assertTrue(resultSet.next(), "ResultSet should have at least one row");
            int accountsCount = resultSet.getInt(1);
            assertEquals(accountsCount, 25, "Expected exactly 25 accounts");
        }
    }

    @Test
    // Verifies that there are no duplicate usernames in the accounts table
    public void testNoDuplicateUsernames() throws Exception {
        String sql = "SELECT username, COUNT(*) c FROM accounts GROUP BY username HAVING c > 1";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            boolean hasDuplicates = false;
            while (resultSet.next()) {
                hasDuplicates = true;
            }
            assertFalse(hasDuplicates, "There should be no duplicate usernames");
        }
    }

    @Test
    // Verifies that there are no duplicate emails in the accounts table
    public void testNoDuplicateEmails() throws Exception {
        String sql = "SELECT email, COUNT(*) c FROM accounts GROUP BY email HAVING c > 1";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            boolean hasDuplicates = false;
            while (resultSet.next()) {
                hasDuplicates = true;
            }
            assertFalse(hasDuplicates, "There should be no duplicate emails");
        }
    }

    @Test
    // Verifies that username, password, and email columns have no NULL values
    public void testNonNullConstraints() throws Exception {
        String sql = "SELECT COUNT(*) FROM accounts WHERE username IS NULL OR password IS NULL OR email IS NULL";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            assertTrue(resultSet.next(), "ResultSet should have a row");
            int nullCount = resultSet.getInt(1);
            assertEquals(nullCount, 0, "No columns should have NULL values");
        }
    }

    @Test
    // Verifies the data for a specific user matches expected values
    public void testSpecificUserData() throws Exception {
        String sql = "SELECT username, password, email FROM accounts WHERE username = 'ShadowFang'";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);

            assertTrue(resultSet.next(), "ShadowFang should exist");
            assertEquals(resultSet.getString("username"), "ShadowFang", "Username should be ShadowFang");
            assertEquals(resultSet.getString("password"), "DragonSlayer", "Password should be DragonSlayer");
            assertEquals(resultSet.getString("email"), "shadowfang@mail.com", "Email should be shadowfang@mail.com");
        }
    }
}