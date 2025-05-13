package com.twozo.app.model;

public class ProductSearchDTO {
    private int id;

    public ProductSearchDTO(){

    }

    public ProductSearchDTO(final int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
