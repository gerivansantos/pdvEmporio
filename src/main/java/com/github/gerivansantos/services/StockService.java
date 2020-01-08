package com.github.gerivansantos.services;

import com.github.gerivansantos.dto.StockDTO;
import com.github.gerivansantos.models.Product;
import com.github.gerivansantos.models.Stock;
import com.github.gerivansantos.repositories.ProductRepository;
import com.github.gerivansantos.repositories.StockRepository;
import com.github.gerivansantos.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class StockService {
    @Autowired
    private StockRepository repo;

    @Autowired
    private ProductRepository productRepository;

    public Stock find(Integer id) {
        Optional<Stock> obj = repo.findById(id);
        //repo.findByProduto_id(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Stock.class.getName()));
    }

    public Stock fromDTO(@Valid StockDTO objDTO) {
        Optional<Product> p = productRepository.findById(objDTO.getIdProduct());
        Product product = new Product(p.get().getId(), p.get().getName(), p.get().getDescription(), p.get().getPrice(), p.get().getRegistrationDate(), p.get().getLast_update());
        return new Stock(objDTO.getIdProduct(), product, objDTO.getAmount(), new Date());
    }

    public Stock update(Stock obj) {
        Stock newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Stock newObj, Stock obj) {
        newObj.setId(obj.getId());
        //newObj.setProduto(obj.getProduto());
        newObj.setAmount(obj.getAmount());
    }


}
