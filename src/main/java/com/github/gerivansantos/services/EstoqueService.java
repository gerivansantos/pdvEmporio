package com.github.gerivansantos.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gerivansantos.dto.EstoqueDTO;
import com.github.gerivansantos.models.Cliente;
import com.github.gerivansantos.models.Estoque;
import com.github.gerivansantos.models.Produto;
import com.github.gerivansantos.repositories.EstoqueRepository;
import com.github.gerivansantos.repositories.ProdutoRepository;
import com.github.gerivansantos.services.exception.ObjectNotFoundException;

@Service
public class EstoqueService {
	@Autowired
	private EstoqueRepository repo;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Estoque find(Integer id) {
		Optional<Estoque> obj = repo.findById(id);
				//repo.findByProduto_id(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Estoque.class.getName()));
	}

	public Estoque fromDTO(@Valid EstoqueDTO objDTO) {
		Optional<Produto> p = produtoRepository.findById(objDTO.getIdProduto());
		Produto produto = new Produto(p.get().getId() , p.get().getNome(), p.get().getPreco());
		return new Estoque(objDTO.getIdProduto(), produto, objDTO.getQuantidade(), objDTO.getUnidadeMedida(), new Date());		
	}

	public Estoque update(Estoque obj) {
		Estoque newObj = find(obj.getId());
		updateData(newObj, obj);	
		return repo.save(newObj);
	}
	
	private void updateData(Estoque newObj, Estoque obj) {
		newObj.setId(obj.getId());
		//newObj.setProduto(obj.getProduto());
		newObj.setQuantidade(obj.getQuantidade());
		newObj.setUnidadeMedida(obj.getUnidadeMedida());		
	}

	
}
