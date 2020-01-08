package com.github.gerivansantos.dto;

import com.github.gerivansantos.models.Product;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private float amount;

    public ProductDTO() {

    }

    public ProductDTO(Product obj) {
        id = obj.getId();
        name = obj.getName();
        price = obj.getPrice();
        amount = (obj.getStock() == null) ? 0 : obj.getStock().getAmount();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
