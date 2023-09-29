package com.balieiro.balieiroFoodapi.domain.service;

import com.balieiro.balieiroFoodapi.domain.entity.Cidade;
import com.balieiro.balieiroFoodapi.domain.entity.Estado;
import com.balieiro.balieiroFoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.balieiro.balieiroFoodapi.infraestructure.repository.CidadeRepository;
import com.balieiro.balieiroFoodapi.infraestructure.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    private CidadeRepository cidadeRepository;

    private EstadoRepository estadoRepository;

    public CadastroCidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade salvar(Cidade cidade){
        Long cidadeId = cidade.getEstado().getId();

        Optional<Estado> estado = estadoRepository.findById(cidadeId);

        if (estado.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de estado com código %d", cidadeId));
        }
        cidade.setEstado(estado.get());
        return cidadeRepository.save(cidade);
    }
}
