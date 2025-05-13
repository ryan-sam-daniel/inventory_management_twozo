package com.twozo.app.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseTransactionRequest {

    private String phoneNo;
    private String method;
    private double amount;
    private List<PurchaseItem> cart;
    private Purchase purchase;

    public PurchaseTransactionRequest() { }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public List<PurchaseItem> getCart() { return cart; }
    public void setCart(List<PurchaseItem> cart) { this.cart = cart; }

    public Purchase getPurchase() { return purchase; }
    public void setPurchase(Purchase purchase) { this.purchase = purchase; }

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
