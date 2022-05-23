package com.he.week12_networkprogramming;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by lenovo on 2022/5/22.
 */

public class DBUtils {
    private static String user = "root";// 用户名
    private static String password = "lizhao20101113"; //密码

    private static Connection getConn(String dbName) {
        Connection connection = null;
        try {
            //动态加载 MySql 的驱动
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://192.168.214.217:3307/" + dbName;
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet queryReader(String reader_num) {
        ResultSet rs = null;
        Connection connection = getConn("library");
        if (connection != null) {
            try {
                PreparedStatement ps;
                Statement smt = connection.createStatement();
                String sql = "select * from readers where reader_number LIKE '%" + reader_num + "%'";
                rs = smt.executeQuery(sql);
                //connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rs;
    }
}
