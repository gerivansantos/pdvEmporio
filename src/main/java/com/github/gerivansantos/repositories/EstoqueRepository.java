package com.github.gerivansantos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.gerivansantos.models.Cliente;
import com.github.gerivansantos.models.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer>  {
	
	@Transactional(readOnly=true)
	Optional<Estoque> findByProduto_id(Integer id);

}
