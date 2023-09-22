package com.balieiro.balieiroFoodapi.api.controller;

import com.balieiro.balieiroFoodapi.domain.entity.Estado;
import com.balieiro.balieiroFoodapi.domain.exception.EntidadeEmUsoException;
import com.balieiro.balieiroFoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.balieiro.balieiroFoodapi.domain.service.CadastroEstadoService;
import com.balieiro.balieiroFoodapi.infraestructure.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estados")
public class EstadoController {

    private EstadoRepository estadoRepository;

    private CadastroEstadoService estadoService;

    public EstadoController(EstadoRepository estadoRepository, CadastroEstadoService estadoService) {
        this.estadoRepository = estadoRepository;
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> lista(){
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
        Estado estado = estadoRepository.buscar(estadoId);

        if (estado != null){
            return ResponseEntity.ok(estado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado){
        return estadoRepository.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        Estado estadoAtual = estadoRepository.buscar(estadoId);

        if (estadoAtual != null){
            BeanUtils.copyProperties(estado, estadoAtual, "id");

            estadoAtual = estadoService.salvar(estadoAtual);

            return ResponseEntity.ok(estadoAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Estado> remover(@PathVariable Long estadoId){
        try {
            estadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();

        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
