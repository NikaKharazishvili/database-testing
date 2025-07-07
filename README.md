### Database Testing Project

- **Tools/Tech**: Java, TestNG, MySQL
- **Description**: A database testing project designed to retrieve and assert database information.
- **Highlights**:
  - Supports testing with MySQL and other databases via configuration
  - Includes a sample `game_accounts.sql` for database setup
  - Configurable through `db.properties` for flexibility
- **Setup Instructions**:
  - Import the `game_accounts.sql` file from the resources folder into your MySQL server to set up the database.
  - Edit `db.properties` with the appropriate database URL, username, and password.
  - Run the tests using `testng.xml`.
  - **Note**: This setup can work with other databases if `db.properties` is updated with the correct JDBC URL and driver. Minor syntax adjustments may be needed in `game_accounts.sql` for compatibility.