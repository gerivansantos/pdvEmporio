package com.github.gerivansantos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gerivansantos.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
