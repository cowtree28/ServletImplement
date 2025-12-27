package org.example.global.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    static {
        try {
            Properties props = new Properties();

            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("application.properties");
            if(in == null) {
                throw new RuntimeException("application.properties not found");
            }

            props.load(in);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

            Class.forName(driver);
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
