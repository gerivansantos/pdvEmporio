package com.github.gerivansantos.dto;

import com.github.gerivansantos.models.Product;

import java.io.Serializable;

public class ProductNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    //@NotEmpty(message="Preenchimento Obrigatório")
    //@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String name;

    //@NotEmpty(message="Preenchimento Obrigatório")
    private Double price;

    //@NotEmpty(message="Preenchimento Obrigatório")
    private float amount;

    //@NotEmpty(message="Preenchimento Obrigatório")
    private String description;

    public ProductNewDTO() {

    }

    public ProductNewDTO(Product obj) {
        name = obj.getName();
        price = obj.getPrice();
        amount = (obj.getEstoque() == null) ? 0 : obj.getEstoque().getQuantidade();
        description = obj.getDescription();
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
