package com.github.gerivansantos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.gerivansantos.models.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>  {

}
