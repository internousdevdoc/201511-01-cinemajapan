package com.internousdev.latterwin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public UserDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int selectUser(String email, String password) {
        String sql = "select user_id from user where email = ? and password = ?";

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/latterwin", "root", "mysql");
            PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, email);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery();) {
                if(rs.next()) {
                    return rs.getInt("user_id");
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
