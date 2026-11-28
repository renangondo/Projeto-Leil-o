package com.leilao.leilao_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leilao.leilao_api.entity.Lance;

public interface  LanceRepository extends JpaRepository<Lance, Long> {
    
    // Todos os lances de um carro, do maior para o menos
    List<Lance> findByCarroIdOrderByValorDesc(Long carroId);

    // Só o maior lance de um carro (se existir)
    Optional<Lance> findFistByCarroIdOrderByValorDesc(Long carroId);

    // Quantidade de lances de um carro
    long countByCarroId(Long carroId);
}
