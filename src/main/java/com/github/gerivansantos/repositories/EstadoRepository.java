package com.github.gerivansantos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.gerivansantos.models.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>  {

}
