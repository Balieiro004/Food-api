package com.balieiro.balieiroFoodapi.infraestructure.repository;

import com.balieiro.balieiroFoodapi.domain.entity.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha,Long> {
//    List<Cozinha> consultaPorNome(String nome);
}
