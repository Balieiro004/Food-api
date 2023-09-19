package com.balieiro.balieiroFoodapi.api.controller;

import com.balieiro.balieiroFoodapi.domain.entity.Estado;
import com.balieiro.balieiroFoodapi.infraestructure.repository.EstadoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estados")
public class EstadoController {

    private EstadoRepository estadoRepository;

    public EstadoController(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @GetMapping
    public List<Estado> lista(){
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoid}")
    public Estado buscar(@PathVariable Long estadoid){
        return estadoRepository.buscar(estadoid);
    }
}
