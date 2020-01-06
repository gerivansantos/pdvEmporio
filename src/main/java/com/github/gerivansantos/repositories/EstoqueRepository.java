package com.github.gerivansantos.repositories;

import com.github.gerivansantos.models.Estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    @Transactional(readOnly = true)
    Optional<Estoque> findByProduct_id(Integer id);

}
