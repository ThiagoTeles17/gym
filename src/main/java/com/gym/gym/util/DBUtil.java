package com.gym.gym.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {

    private static final String url = "jdbc:mysql://localhost:3306/gym";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection conn;

    public static Connection getConnection() {

        try {
            if(conn == null) {
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }
            else {
                return conn;
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
            return null;
        }


    };

}
