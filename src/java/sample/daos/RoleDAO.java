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
import sample.dtos.RoleDTO;
import sample.utils.DBUtil;

/**
 *
 * @author Dung
 */
public class RoleDAO implements Serializable{

    public RoleDTO getUserRole(int roleID) throws SQLException {
        RoleDTO currRole = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT roleName FROM tblRoles WHERE roleID=" + roleID;
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String roleName = rs.getString("roleName");
                    currRole = new RoleDTO(roleID, roleName);
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
        return currRole;
    }
}
