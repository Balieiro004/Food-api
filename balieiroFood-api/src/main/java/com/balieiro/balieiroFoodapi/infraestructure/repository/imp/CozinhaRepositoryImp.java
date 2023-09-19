package com.balieiro.balieiroFoodapi.infraestructure.repository.imp;

import com.balieiro.balieiroFoodapi.domain.entity.Cozinha;
import com.balieiro.balieiroFoodapi.infraestructure.repository.CozinhaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CozinhaRepositoryImp implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar(){
        TypedQuery<Cozinha> query =  manager.createQuery("from Cozinha", Cozinha.class);
        return query.getResultList();
    }
    @Override
    public  Cozinha buscar(Long id){
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Long id){
        Cozinha cozinha = buscar(id);

        if (cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cozinha);
    }
}