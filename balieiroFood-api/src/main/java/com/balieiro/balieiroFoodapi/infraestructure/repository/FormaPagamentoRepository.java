package com.balieiro.balieiroFoodapi.infraestructure.repository;

import com.balieiro.balieiroFoodapi.domain.entity.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {

    List<FormaPagamento> listar();
    FormaPagamento buscar(Long id);
    FormaPagamento salvar (FormaPagamento formaPagamento);
    void remover(FormaPagamento formaPagamento);
}
