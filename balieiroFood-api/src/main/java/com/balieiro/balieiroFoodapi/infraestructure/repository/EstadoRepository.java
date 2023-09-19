package com.balieiro.balieiroFoodapi.infraestructure.repository;

import com.balieiro.balieiroFoodapi.domain.entity.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar (Estado estado);
    void remover(Estado estado);
}
