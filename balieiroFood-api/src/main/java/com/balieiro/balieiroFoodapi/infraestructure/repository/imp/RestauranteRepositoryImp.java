package com.balieiro.balieiroFoodapi.infraestructure.repository.imp;

import com.balieiro.balieiroFoodapi.domain.entity.Restaurante;
import com.balieiro.balieiroFoodapi.infraestructure.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class RestauranteRepositoryImp implements RestauranteRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Restaurante> listar() {
        TypedQuery<Restaurante> query = manager.createQuery("from Restaurante", Restaurante.class);

        return query.getResultList();
    }

    @Override
    public Restaurante buscar(Long id) {
        return manager.find(Restaurante.class, id);
    }
    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }
    @Transactional
    @Override
    public void remover(Restaurante restaurante) {
        restaurante = buscar(restaurante.getId());
        manager.remove(restaurante);
    }
}
