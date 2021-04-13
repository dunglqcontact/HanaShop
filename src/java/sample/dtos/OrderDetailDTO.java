/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dung
 */
public class OrderDetailDTO implements Serializable{

    private Map<Integer, ProductDTO> cart;
    private int orderDetailID;
    private int orderID;
    private int productID;
    private int quantity;
    private float total;
    private String productName;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int quantity, float total, String productName) {
        this.quantity = quantity;
        this.total = total;
        this.productName = productName;
    }

    public OrderDetailDTO(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public OrderDetailDTO(Map<Integer, ProductDTO> cart, int orderDetailID, int orderID, int productID, int quantity, float total) {
        this.cart = cart;
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.total = total;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Map<Integer, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, ProductDTO> cart) {
        this.cart = cart;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void add(ProductDTO dto) {
        if (this.cart == null) {
            this.cart = new HashMap<Integer, ProductDTO>();
        }
        if (!this.cart.containsKey(dto.getProductID())) {
            dto.setQuantity(1);
        }
        if (this.cart.containsKey(dto.getProductID())) {
            int quantity = this.cart.get(dto.getProductID()).getQuantity();
            dto.setQuantity(quantity + 1);
        }
        float totalPrice = dto.getPrice() * dto.getQuantity();
        dto.setTotalPrice(totalPrice);
        cart.put(dto.getProductID(), dto);
        Calculate();
    }

    public void delete(int id) {
        if (cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            cart.remove(id);
        }
        Calculate();
    }

    public void update(int id, ProductDTO dto) {
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.replace(id, dto);
            }
        }
        float totalPrice = dto.getPrice() * dto.getQuantity();
        dto.setTotalPrice(totalPrice);
        Calculate();
    }

    public void Calculate() {
        float total = 0;
        for (int id : cart.keySet()) {
            total += cart.get(id).getPrice() * cart.get(id).getQuantity();
        }
        setTotal(total);
    }
}
