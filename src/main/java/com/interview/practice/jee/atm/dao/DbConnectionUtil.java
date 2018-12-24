package com.interview.practice.jee.atm.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DbConnectionUtil {
    private static final Logger LOGGER = Logger.getLogger(DbConnectionUtil.class.getName());

    private static final String DRIVER_PROPERTY_NAME = "driver";
    private static final String URL_PROPERTY_NAME = "url";
    private static final String USER_PROPERTY_NAME = "user";
    private static final String PASSWORD_PROPERTY_NAME = "password";

    private DbConnectionUtil() {}

    private static final Properties DB_PROPERTIES;

    static {
        DB_PROPERTIES = new Properties();
        InputStream inputStream = DbConnectionUtil.class.getResourceAsStream("/db.properties");
        try {
            DB_PROPERTIES.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Cannot get DB connection properties", e);
        }
        try {
            Class.forName(DB_PROPERTIES.getProperty(DRIVER_PROPERTY_NAME));
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Cannot load DB driver", e);;
        }
        LOGGER.log(Level.INFO, "DB properties are successfully loaded");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_PROPERTIES.getProperty(URL_PROPERTY_NAME),
                DB_PROPERTIES.getProperty(USER_PROPERTY_NAME),
                DB_PROPERTIES.getProperty(PASSWORD_PROPERTY_NAME)
        );
    }

}
