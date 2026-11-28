package com.leilao.leilao_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leilao.leilao_api.entity.Carro;

public interface  CarroRepository extends JpaRepository<Carro, Long> {
    
}
