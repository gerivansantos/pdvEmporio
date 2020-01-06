package com.github.gerivansantos.services;

import com.github.gerivansantos.dto.ProductDTO;
import com.github.gerivansantos.dto.ProductNewDTO;
import com.github.gerivansantos.models.Categoria;
import com.github.gerivansantos.models.Estoque;
import com.github.gerivansantos.models.Product;
import com.github.gerivansantos.repositories.CategoriaRepository;
import com.github.gerivansantos.repositories.EstoqueRepository;
import com.github.gerivansantos.repositories.ProductRepository;
import com.github.gerivansantos.services.exception.DataIntegrityException;
import com.github.gerivansantos.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Product find(Integer id) {
        Optional<Product> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
    }

    @Transactional
    public Product insert(Product obj) {
        obj.setId(null);
        obj = repo.save(obj);
        estoqueRepository.save(obj.getEstoque());
        return obj;
    }

    public Page<Product> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repo.search(nome, categorias, pageRequest);
    }

    public List<Product> findAll() {
        return repo.findAllByOrderByNameAsc();
    }

    public Product fromDTO(@Valid ProductNewDTO objDTO) {
        Product product = new Product(null, objDTO.getName(), objDTO.getDescription(), objDTO.getPrice());
        Estoque estoque = new Estoque(null, product, objDTO.getAmount(), new Date());

        product.setEstoque(estoque);

        return product;
    }

    public Product fromDTO(@Valid ProductDTO objDTO) {
        return new Product(null, objDTO.getName(), objDTO.getDescription(), objDTO.getPrice());
    }

    public void delete(Integer id) {
        Product p = find(id);
        try {
            if (p.getEstoque().getQuantidade() != 0) {
                throw new DataIntegrityException("Não é possível excluir o produto porque há estoque relacionado");
            } else {
                repo.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o produto porque há itens com esse produto");
        }

    }

    public Product update(Product obj) {
        Product newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Product newObj, Product obj) {
        newObj.setName(obj.getName());
        newObj.setDescription(obj.getDescription());
        newObj.setPrice(obj.getPrice());
        newObj.getEstoque().setQuantidade(obj.getEstoque().getQuantidade());
    }


}
