package com.leilao.leilao_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leilao.leilao_api.dto.CarroRequest;
import com.leilao.leilao_api.dto.CarroResponse;
import com.leilao.leilao_api.service.CarroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public List<CarroResponse> listar() {
        return carroService.listar();
    }

    @GetMapping("/id")
    public CarroResponse buscarPorId(@PathVariable Long id ) {
        return carroService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarroResponse cadastrar(@RequestBody @Valid CarroRequest dados) {
        return carroService.cadastrar(dados);
    }

    
}
