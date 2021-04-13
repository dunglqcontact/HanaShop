/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

import java.io.Serializable;

/**
 *
 * @author Dung
 */
public class ProductDTOError implements Serializable{

    private String productIDError;
    private String productNameError;
    private String descriptionError;
    private String priceError;
    private String dateError;
    private String categoryIDError;
    private String quantityError;
    private String imageDataError;
    private String statusError;
    private String userIDError;
    private String updateDateError;
    private String totalPriceError;

    public ProductDTOError() {
    }

    public ProductDTOError(String productIDError, String productNameError, String descriptionError, String priceError, String dateError, String categoryIDError, String quantityError, String imageDataError, String statusError, String userIDError, String updateDateError, String totalPriceError) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.descriptionError = descriptionError;
        this.priceError = priceError;
        this.dateError = dateError;
        this.categoryIDError = categoryIDError;
        this.quantityError = quantityError;
        this.imageDataError = imageDataError;
        this.statusError = statusError;
        this.userIDError = userIDError;
        this.updateDateError = updateDateError;
        this.totalPriceError = totalPriceError;
    }

    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getDateError() {
        return dateError;
    }

    public void setDateError(String dateError) {
        this.dateError = dateError;
    }

    public String getCategoryIDError() {
        return categoryIDError;
    }

    public void setCategoryIDError(String categoryIDError) {
        this.categoryIDError = categoryIDError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getImageDataError() {
        return imageDataError;
    }

    public void setImageDataError(String imageDataError) {
        this.imageDataError = imageDataError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getUpdateDateError() {
        return updateDateError;
    }

    public void setUpdateDateError(String updateDateError) {
        this.updateDateError = updateDateError;
    }

    public String getTotalPriceError() {
        return totalPriceError;
    }

    public void setTotalPriceError(String totalPriceError) {
        this.totalPriceError = totalPriceError;
    }

}
