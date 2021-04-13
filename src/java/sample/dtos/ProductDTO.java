/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Dung
 */
public class ProductDTO implements Serializable{

    private int productID;
    private String productName;
    private String description;
    private float price;
    private Date date;
    private int categoryID;
    private int quantity;
    private String imageData;
    private boolean status;
    private String userID;
    private Date updateDate;
    private float totalPrice;

    public ProductDTO() {
    }

    public ProductDTO(int productID, String productName, float price, float totalPrice) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public ProductDTO(int productID, String productName, float price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(int productID, String productName, String description, float price, Date date, int categoryID, int quantity, String imageData, boolean status) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.date = date;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.imageData = imageData;
        this.status = status;
    }

    public ProductDTO(int productID, String productName, String description, float price, Date date, int categoryID, int quantity, String imageData, boolean status, String userID, Date updateDate) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.date = date;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.imageData = imageData;
        this.status = status;
        this.userID = userID;
        this.updateDate = updateDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
