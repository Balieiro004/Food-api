package com.balieiro.balieiroFoodapi.infraestructure.repository;

import com.balieiro.balieiroFoodapi.domain.entity.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);
}
