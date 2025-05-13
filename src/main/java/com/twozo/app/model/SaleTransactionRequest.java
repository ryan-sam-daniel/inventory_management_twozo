package com.twozo.app.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleTransactionRequest {
    private String phoneNo;
    private String method;
    private double amount;
    private List<SaleItem> cart;
    private Sale sale;

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public List<SaleItem> getCart() { return cart; }
    public void setCart(List<SaleItem> cart) { this.cart = cart; }

    public Sale getSale() { return sale; }
    public void setSale(Sale sale) { this.sale = sale; }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
