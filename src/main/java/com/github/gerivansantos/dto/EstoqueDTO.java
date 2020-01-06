package com.github.gerivansantos.dto;

import java.io.Serializable;

public class EstoqueDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idProduto;	
	private float quantidade = 0;
	private String unidadeMedida = "UND";
	
	public EstoqueDTO() {
		
	}

	public EstoqueDTO(Integer idProduto, float quantidade, String unidadeMedida) {
		super();
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.unidadeMedida = unidadeMedida;
	}
	
	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	

}
