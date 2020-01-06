package com.github.gerivansantos.services;

import com.github.gerivansantos.dto.EstoqueDTO;
import com.github.gerivansantos.models.Estoque;
import com.github.gerivansantos.models.Product;
import com.github.gerivansantos.repositories.EstoqueRepository;
import com.github.gerivansantos.repositories.ProductRepository;
import com.github.gerivansantos.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository repo;

    @Autowired
    private ProductRepository productRepository;

    public Estoque find(Integer id) {
        Optional<Estoque> obj = repo.findById(id);
        //repo.findByProduto_id(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Estoque.class.getName()));
    }

    public Estoque fromDTO(@Valid EstoqueDTO objDTO) {
        Optional<Product> p = productRepository.findById(objDTO.getIdProduto());
        Product product = new Product(p.get().getId(), p.get().getName(), p.get().getDescription(), p.get().getPrice(), p.get().getRegistrationDate(), p.get().getLast_update());
        return new Estoque(objDTO.getIdProduto(), product, objDTO.getQuantidade(), new Date());
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
    }


}
