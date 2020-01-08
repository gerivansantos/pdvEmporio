package com.github.gerivansantos.repositories;

import com.github.gerivansantos.models.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Transactional(readOnly = true)
    Optional<Stock> findByProduct_id(Integer id);

}
