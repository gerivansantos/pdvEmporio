package com.github.gerivansantos.controllers;

import com.github.gerivansantos.dto.StockDTO;
import com.github.gerivansantos.models.Stock;
import com.github.gerivansantos.services.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    private StockService service;

    @RequestMapping(value = "/{idProduct}", method = RequestMethod.GET)
    public ResponseEntity<Stock> find(@PathVariable Integer idProduct) {
        Stock obj = service.find(idProduct);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{idProduct}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody StockDTO objDTO, @PathVariable Integer idProduct) {
        objDTO.setIdProduct(idProduct);
        Stock obj = service.fromDTO(objDTO);
        obj.setId(idProduct);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

}
