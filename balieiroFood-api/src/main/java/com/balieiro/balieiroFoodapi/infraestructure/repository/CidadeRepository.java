package com.balieiro.balieiroFoodapi.infraestructure.repository;

import com.balieiro.balieiroFoodapi.domain.entity.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar (Cidade cidade);
    void remover(Cidade cidade);
}
