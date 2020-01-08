package com.github.gerivansantos.dto;

import java.io.Serializable;

public class StockDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idProduct;
    private float amount = 0;

    public StockDTO() {

    }

    public StockDTO(Integer idProduct, float amount) {
        super();
        this.idProduct = idProduct;
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }


}
