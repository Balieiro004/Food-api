package com.balieiro.balieiroFoodapi.domain.service;

import com.balieiro.balieiroFoodapi.domain.entity.Cozinha;
import com.balieiro.balieiroFoodapi.domain.exception.EntidadeEmUsoException;
import com.balieiro.balieiroFoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.balieiro.balieiroFoodapi.infraestructure.repository.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    private CozinhaRepository cozinhaRepository;

    public CadastroCozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId){
        try {
            cozinhaRepository.deleteById(cozinhaId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }
}
