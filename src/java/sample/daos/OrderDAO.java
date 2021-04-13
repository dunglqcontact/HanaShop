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
import java.util.ArrayList;
import java.util.Date;
import sample.dtos.OrderDTO;
import sample.dtos.OrderDetailDTO;
import sample.dtos.ProductDTO;
import sample.utils.DBUtil;

/**
 *
 * @author Dung
 */
public class OrderDAO implements Serializable{

    public void createAnOrder(Date date, String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT tblOrders(orderDate, userID) VALUES (?,?)";
                pst = conn.prepareStatement(sql);
                java.sql.Date availDate = new java.sql.Date(date.getTime());
                pst.setDate(1, availDate);
                pst.setString(2, userID);
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

    public void insertOrderDetails(int orderID, OrderDetailDTO orderDetail) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT tblOrderDetails(orderID, productID, quantity, total) VALUES (?,?,?,?)";
                pst = conn.prepareStatement(sql);
                for (ProductDTO dto : orderDetail.getCart().values()) {
                    pst.setInt(1, orderID);
                    pst.setInt(2, dto.getProductID());
                    pst.setInt(3, dto.getQuantity());
                    pst.setFloat(4, dto.getTotalPrice());
                    int quantityProduct = getQuantity(dto.getProductID());
                    updateCurrentQuantity(dto, quantityProduct);
                    pst.addBatch();
                }
                pst.executeBatch();
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

    public void setOrderTotalPrice(int orderID, float totalPrice) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblOrders SET price=? WHERE orderID=?";
                pst = conn.prepareStatement(sql);
                pst.setFloat(1, totalPrice);
                pst.setInt(2, orderID);
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

    public OrderDTO getNewestOrder(String userID) throws SQLException {
        OrderDTO order = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT orderID, price, orderDate FROM tblOrders WHERE userID=? ORDER BY orderID desc";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    float price = rs.getFloat("price");
                    Date date = rs.getDate("orderDate");
                    order = new OrderDTO(orderID, date, userID, price);
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
        return order;
    }

    public ArrayList<OrderDTO> getOrderByUserID(String userID) throws SQLException {
        ArrayList<OrderDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT orderID, price, orderDate FROM tblOrders WHERE userID=? ORDER BY orderID desc";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    float price = rs.getFloat("price");
                    Date date = rs.getDate("orderDate");
                    orderList.add(new OrderDTO(orderID, date, userID, price));
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
        return orderList;
    }

    public ArrayList<OrderDTO> getOrderByDate(String userID, String dateString) throws SQLException {
        ArrayList<OrderDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT orderID, price, orderDate FROM tblOrders WHERE userID=? AND orderDate='" + dateString + "' ORDER BY orderID desc";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    float price = rs.getFloat("price");
                    Date date = rs.getDate("orderDate");
                    orderList.add(new OrderDTO(orderID, date, userID, price));
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
        return orderList;
    }

    public ArrayList<OrderDTO> getOrderByName(String userID, String searchName) throws SQLException {
        ArrayList<OrderDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = " SELECT o.orderID, o.price, orderDate FROM tblOrders o join tblOrderDetails od on o.orderID = od.orderID join tblProducts p on od.productID = p.productID WHERE o.userID=? AND p.productName LIKE '%" + searchName + "%' ORDER BY o.orderID desc";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    float price = rs.getFloat("price");
                    Date date = rs.getDate("orderDate");
                    orderList.add(new OrderDTO(orderID, date, userID, price));
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
        return orderList;
    }

    public ArrayList<Date> getDateOfOrder(String userID) throws SQLException {
        ArrayList<Date> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = " SELECT orderDate FROM tblOrders WHERE userID=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    Date date = rs.getDate("orderDate");
                    result.add(date);
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
        return result;
    }

    public ArrayList<OrderDetailDTO> getOrderDetailByOrderID(int orderID) throws SQLException {
        ArrayList<OrderDetailDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT p.productName, o.quantity, o.total FROM tblOrderDetails o join tblProducts p on o.productID = p.productID where orderID ='" + orderID + "'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    int quantity = rs.getInt("quantity");
                    float total = rs.getFloat("total");
                    orderList.add(new OrderDetailDTO(quantity, total, productName));
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
        return orderList;
    }

    private int getQuantity(int productID) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT quantity FROM tblProducts  WHERE productID=" + productID;
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("quantity");
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
        return result;
    }

    private void updateCurrentQuantity(ProductDTO dto, int quantityProduct) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "update tblProducts set quantity=? WHERE productID=?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, quantityProduct - dto.getQuantity());
                pst.setInt(2, dto.getProductID());
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
}
