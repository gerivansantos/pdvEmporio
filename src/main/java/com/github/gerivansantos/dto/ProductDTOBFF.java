package com.github.gerivansantos.dto;

import java.io.Serializable;
import java.util.List;

public class ProductDTOBFF implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long total_count;
    private List<ProductDTO> items;


    public ProductDTOBFF() {

    }

    public ProductDTOBFF(Long total_count, List<ProductDTO> items) {
        this.total_count = total_count;
        this.items = items;
    }

    public Long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Long total_count) {
        this.total_count = total_count;
    }

    public List<ProductDTO> getItems() {
        return items;
    }

    public void setItems(List<ProductDTO> items) {
        this.items = items;
    }
}
