/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sample.dtos.UserDTO;
import sample.utils.DBUtil;

/**
 *
 * @author Dung
 */
public class UserDAO implements Serializable{

    public void registerAsCustomer(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT tblUsers(userID, fullName, password, roleID, address, dateOfBirth) VALUES(?,?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, user.getUserID());
                pst.setString(2, user.getFullName());
                pst.setString(3, user.getPassword());
                pst.setInt(4, user.getRoleID());
                pst.setString(5, user.getAddress());
                java.sql.Date dateOfBirth = new java.sql.Date(user.getDateOfBirth().getTime());
                pst.setDate(6, dateOfBirth);
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public UserDTO login(String userID, String password) throws SQLException {
        UserDTO crtUser = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT fullName, address, dateOfBirth, roleID FROM tblUsers WHERE userID='" + userID + "' and password='" + password + "'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String address = rs.getString("address");
                    Date dateOfBirth = rs.getDate("dateOfBirth");
                    int roleID = rs.getInt("roleID");
                    crtUser = new UserDTO(userID, fullName, password, roleID, address, dateOfBirth);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return crtUser;
    }

    public boolean checkAccountExist(String userID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT fullname FROM tblUsers WHERE userID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
