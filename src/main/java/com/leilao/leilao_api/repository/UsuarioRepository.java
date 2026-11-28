package com.leilao.leilao_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leilao.leilao_api.entity.Usuario;


public  interface  UsuarioRepository extends  JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String Email);

    boolean existsByEmail(String Email);
}