package com.balieiro.balieiroFoodapi.infraestructure.repository;

import com.balieiro.balieiroFoodapi.domain.entity.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> listar();
    Restaurante buscar(Long id);
    Restaurante salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);
}
