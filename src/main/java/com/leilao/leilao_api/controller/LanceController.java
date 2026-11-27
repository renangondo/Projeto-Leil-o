package com.leilao.leilao_api.controller;

import com.leilao.leilao_api.dto.LanceRequest;
import com.leilao.leilao_api.dto.LanceResponse;
import com.leilao.leilao_api.service.LanceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros/{carroId}/lances")
public class LanceController {

    private final LanceService lanceService;

    public LanceController(LanceService lanceService) {
        this.lanceService = lanceService;
    }

    @GetMapping
    public List<LanceResponse> listar(@PathVariable Long carroId) {
        return lanceService.listarPorCarro(carroId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LanceResponse darLance(@PathVariable Long carroId, @RequestBody @Valid LanceRequest dados) {
        return lanceService.darLance(carroId, dados);
    }
}