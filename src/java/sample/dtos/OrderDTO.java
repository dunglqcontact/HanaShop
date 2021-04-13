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
public class OrderDTO implements Serializable{

    private int orderID;
    private Date orderDate;
    private String userID;
    private float price;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, Date orderDate, String userID, float price) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.userID = userID;
        this.price = price;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
