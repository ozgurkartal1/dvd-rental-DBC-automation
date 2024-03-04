package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    private static final Logger LOGGER = LogManager.getLogger(DBUtils.class);

    private static final String URL = ConfigManager.getProperty("db_URL");
    private static final String USERNAME = ConfigManager.getProperty("db_Username");

    private static final String PASSWORD = System.getenv("db-password");

    public static Connection connection;
    public static PreparedStatement preparedStatement;

    public static void getConnection() {
        try {
            LOGGER.info("Try to get connection ");
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (SQLException e) {
            LOGGER.error("Connection is failed.");
            throw new RuntimeException(e);
        }
    }

    public static void createStatement(String query){
        try {
            LOGGER.info("Try to create statement.");
            getConnection();
           preparedStatement = connection.prepareStatement(query);

        } catch (SQLException e) {
            LOGGER.error("Creating statement process is failed.");
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> executeQuery(String query , RowMapper<T> rm){
        List<T> results = new ArrayList<>();
        try {
            createStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                results.add(rm.mapRow(rs));
            }
            connection.close();
        }catch (SQLException e) {
            LOGGER.error("The results are not created.");
            throw new RuntimeException(e);
        }
        LOGGER.info("The results are created.");

        return results;
    }

}
