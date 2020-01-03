package com.github.gerivansantos.services;

import com.github.gerivansantos.dto.ProdutoDTO;
import com.github.gerivansantos.dto.ProdutoNewDTO;
import com.github.gerivansantos.models.Categoria;
import com.github.gerivansantos.models.Estoque;
import com.github.gerivansantos.models.Produto;
import com.github.gerivansantos.repositories.CategoriaRepository;
import com.github.gerivansantos.repositories.EstoqueRepository;
import com.github.gerivansantos.repositories.ProdutoRepository;
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
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Produto find(Integer id) {
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    @Transactional
    public Produto insert(Produto obj) {
        obj.setId(null);
        obj = repo.save(obj);
        estoqueRepository.save(obj.getEstoque());
        return obj;
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repo.search(nome, categorias, pageRequest);
    }

    public List<Produto> findAll() {
        return repo.findAllByOrderByNameAsc();
    }

    public Produto fromDTO(@Valid ProdutoNewDTO objDTO) {
        Produto produto = new Produto(null, objDTO.getName(), objDTO.getDescription(), objDTO.getPrice());
        Estoque estoque = new Estoque(null, produto, objDTO.getAmount(), new Date());

        produto.setEstoque(estoque);

        return produto;
    }

    public Produto fromDTO(@Valid ProdutoDTO objDTO) {
        return new Produto(null, objDTO.getName(), objDTO.getDescription(), objDTO.getPrice());
    }

    public void delete(Integer id) {
        Produto p = find(id);
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

    public Produto update(Produto obj) {
        Produto newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Produto newObj, Produto obj) {
        newObj.setName(obj.getName());
        newObj.setDescription(obj.getDescription());
        newObj.setPrice(obj.getPrice());
        newObj.getEstoque().setQuantidade(obj.getEstoque().getQuantidade());
    }


}
