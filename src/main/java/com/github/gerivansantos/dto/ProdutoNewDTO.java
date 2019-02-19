package com.github.gerivansantos.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.github.gerivansantos.models.Produto;

public class ProdutoNewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//@NotEmpty(message="Preenchimento Obrigatório")
	//@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	//@NotEmpty(message="Preenchimento Obrigatório")
	private Double preco;
	
	//@NotEmpty(message="Preenchimento Obrigatório")
	private float quantidade;
	
	//@NotEmpty(message="Preenchimento Obrigatório")
	private String unidadeMedida;
	
	public ProdutoNewDTO() {
		
	}
	
	public ProdutoNewDTO(Produto obj) {
		nome = obj.getNome();
		preco = obj.getPreco();		
		quantidade = (obj.getEstoque() == null) ? 0 : obj.getEstoque().getQuantidade();
		unidadeMedida = (obj.getEstoque() == null) ? "UND" : obj.getEstoque().getUnidadeMedida();
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
	
	

}
