package com.github.gerivansantos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.gerivansantos.dto.EstoqueDTO;
import com.github.gerivansantos.models.Estoque;
import com.github.gerivansantos.services.EstoqueService;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueController {

	@Autowired
	private EstoqueService service;

	@RequestMapping(value = "/{idProduto}", method = RequestMethod.GET)
	public ResponseEntity<Estoque> find(@PathVariable Integer idProduto) {
		Estoque obj = service.find(idProduto);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{idProduto}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EstoqueDTO objDTO, @PathVariable Integer idProduto) {
		objDTO.setIdProduto(idProduto);
		Estoque obj = service.fromDTO(objDTO);
		obj.setId(idProduto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

}
