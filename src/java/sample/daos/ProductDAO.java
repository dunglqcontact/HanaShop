/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.daos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import javax.servlet.http.Part;
import sample.dtos.ProductDTO;
import sample.utils.DBUtil;

/**
 *
 * @author Dung
 */
public class ProductDAO implements Serializable {

    private String encodeImageToBase64String(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        outputStream.close();

        return base64Image;
    }

    public int countProduct() throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT productID FROM tblProducts";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    result++;
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

    public int countPagingProductByName(int status, String searchName) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT productID FROM tblProducts WHERE productName LIKE '%" + searchName +"%' AND status = " + status;
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    result++;
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
    
    public int countPagingProductByPrice(int status, float minPrice, float maxPrice) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT productID FROM tblProducts WHERE price >= " + minPrice + " AND price <= " + maxPrice + " AND status = " + status;
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    result++;
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

    public int countPagingProductByCategory(int status, int categoryID) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "SELECT productID FROM tblProducts WHERE categoryID = " + categoryID + " AND status = " + status;
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    result++;
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
    
    public void insertNewProduct(String productName, String description, float price, Date dateNon, int categoryID, int quantity, Part imagePart, boolean status) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "INSERT tblProducts(productName, "
                        + "description, price, createDate, categoryID, "
                        + "quantity, image, status) VALUES (?,?,?,?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, productName);
                pst.setString(2, description);
                pst.setFloat(3, price);
                java.sql.Date availDate = new java.sql.Date(dateNon.getTime());
                pst.setDate(4, availDate);
                pst.setInt(5, categoryID);
                pst.setInt(6, quantity);
                pst.setBinaryStream(7, imagePart.getInputStream(), (int) imagePart.getSize());
                pst.setBoolean(8, true);
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

    public void updateProduct(String productID, String productName,
            String description, float price, Date dateNon, int categoryID,
            int quantity, Part imagePart, boolean status, String userID,
            Date date) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "update tblProducts set productName=?, "
                        + "description=?, price=?, categoryID=?, quantity=?, "
                        + "image=?, status=?, userID=?, updateDate=? "
                        + "WHERE productID=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, productName);
                pst.setString(2, description);
                pst.setFloat(3, price);
                pst.setInt(4, categoryID);
                pst.setInt(5, quantity);
                pst.setBinaryStream(6, imagePart.getInputStream(), (int) imagePart.getSize());
                pst.setBoolean(7, status);
                pst.setString(8, userID);
                java.sql.Date availDate = new java.sql.Date(date.getTime());
                pst.setDate(9, availDate);
                pst.setString(10, productID);
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

    public ArrayList<ProductDTO> searchProductsByName(String searchName, int status, int index, int pageSize) throws SQLException {
        ArrayList<ProductDTO> searchList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
//                String sql = "SELECT productID, productName, description, price, "
//                        + "createDate, categoryID, quantity, image, status FROM "
//                        + "tblProducts WHERE productName LIKE '%" + searchName
//                        + "%' AND status = " + status
//                        + " ORDER BY createDate DESC";
                String sql = "WITH x AS(SELECT  ROW_NUMBER() OVER (ORDER BY createDate DESC) AS r "
                        + ", * FROM tblProducts WHERE productName LIKE '%" + searchName + "%' AND status = " + status + ")"
                        + " SELECT * FROM x WHERE r between ((" + index + " - 1) * " + pageSize + ") + 1 "
                        + "and " + index + " * " + pageSize + "";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    int categoryID = rs.getInt("categoryID");
                    InputStream inputStream = rs.getBinaryStream("image");
                    String base64Image = encodeImageToBase64String(inputStream);
                    inputStream.close();
                    boolean statusB = rs.getBoolean("status");
                    searchList.add(new ProductDTO(productID, productName, description, price, createDate, categoryID, productID, base64Image, statusB));
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
        return searchList;
    }

    public ArrayList<ProductDTO> searchProductsByPrice(float minPrice, float maxPrice, int status, int index, int pageSize) throws SQLException {
        ArrayList<ProductDTO> searchList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "WITH x AS(SELECT  ROW_NUMBER() OVER (ORDER BY createDate DESC) AS r\n"
                        + ", * from tblProducts WHERE price >= " + minPrice + " AND price <= " + maxPrice + " AND status = " + status + ")\n"
                        + "SELECT * FROM x WHERE r between ((" + index + " - 1) * " + pageSize + ") + 1 "
                        + "and " + index + " * " + pageSize + "";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    int categoryID = rs.getInt("categoryID");
                    InputStream inputStream = rs.getBinaryStream("image");
                    String base64Image = encodeImageToBase64String(inputStream);
                    inputStream.close();
                    boolean statusB = rs.getBoolean("status");
                    searchList.add(new ProductDTO(productID, productName, description, price, createDate, categoryID, productID, base64Image, statusB));
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
        return searchList;
    }

    public ArrayList<ProductDTO> searchProductsByCategory(int categoryID, int status, int index, int pageSize) throws SQLException {
        ArrayList<ProductDTO> searchList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "WITH x AS(SELECT  ROW_NUMBER() OVER (ORDER BY createDate DESC) AS r\n"
                        + ", * from tblProducts WHERE categoryID = " + categoryID + " AND status = " + status + " )\n"
                        + "SELECT * FROM x WHERE r between ((" + index + " - 1) * " + pageSize + ") + 1 "
                        + "and " + index + " * " + pageSize + "";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    Date createDate = rs.getDate("createDate");
                    InputStream inputStream = rs.getBinaryStream("image");
                    String base64Image = encodeImageToBase64String(inputStream);
                    inputStream.close();
                    boolean statusB = rs.getBoolean("status");
                    searchList.add(new ProductDTO(productID, productName, description, price, createDate, categoryID, productID, base64Image, statusB));
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
        return searchList;
    }

    public void deleteProduct(String productID, String userID, Date date) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                String sql = "update tblProducts set status=0, userID=?, updateDate=? WHERE productID=?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userID);
                java.sql.Date availDate = new java.sql.Date(date.getTime());
                pst.setDate(2, availDate);
                pst.setString(3, productID);
                pst.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
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
