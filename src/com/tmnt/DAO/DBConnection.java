package com.tmnt.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tmnt on 2015/12/27.
 */
public class DBConnection {
    private static String DBNAME="oracle.jdbc.driver.OracleDriver";
    private static String URL="jdbc:oracle:thin:@localhost:1521:tmntduke";
    private static String USER="scott";
    private static String PASSWORD="tiger";
    private static Connection connection;
    public static Connection getConn(){
        try {
            Class.forName(DBNAME);
            try {
                connection= DriverManager.getConnection(URL,USER,PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  connection;
    }

    public  void closeConn(){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
