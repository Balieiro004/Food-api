package com.balieiro.balieiroFoodapi.infraestructure.repository.imp;

import com.balieiro.balieiroFoodapi.domain.entity.FormaPagamento;
import com.balieiro.balieiroFoodapi.infraestructure.repository.FormaPagamentoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class FormaPagamentoRepositoryImp implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> listar() {
        TypedQuery<FormaPagamento> query = manager.createQuery("from FormaPagamento", FormaPagamento.class);
        return query.getResultList();
    }

    @Override
    public FormaPagamento buscar(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {

        return manager.merge(formaPagamento);
    }
    @Transactional
    @Override
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = buscar(formaPagamento.getId());
        manager.remove(formaPagamento);
    }
}
