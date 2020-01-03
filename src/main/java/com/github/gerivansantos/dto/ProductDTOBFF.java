package com.github.gerivansantos.dto;

import java.io.Serializable;
import java.util.List;

public class ProdutoDTOBFF implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long total_count;
    private List<ProdutoDTO> items;


    public ProdutoDTOBFF() {

    }

    public ProdutoDTOBFF(Long total_count, List<ProdutoDTO> items) {
        this.total_count = total_count;
        this.items = items;
    }

    public Long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Long total_count) {
        this.total_count = total_count;
    }

    public List<ProdutoDTO> getItems() {
        return items;
    }

    public void setItems(List<ProdutoDTO> items) {
        this.items = items;
    }
}
