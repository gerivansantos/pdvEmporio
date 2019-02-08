package com.github.gerivansantos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gerivansantos.models.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
