package com.balieiro.balieiroFoodapi.api.controller;

import com.balieiro.balieiroFoodapi.domain.entity.Cidade;
import com.balieiro.balieiroFoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.balieiro.balieiroFoodapi.domain.service.CadastroCidadeService;
import com.balieiro.balieiroFoodapi.infraestructure.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private CidadeRepository cidadeRepository;

    private CadastroCidadeService cidadeService;

    public CidadeController(CidadeRepository cidadeRepository, CadastroCidadeService cidadeService) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.listar();
    }
    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId){
        Cidade cidade = cidadeRepository.buscar(cidadeId);
        if (cidade != null){
            return ResponseEntity.ok(cidade);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
        try{
            cidade = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cidade);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
        try {
            Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);

            if (cidadeAtual != null) {
                BeanUtils.copyProperties(cidade, cidadeAtual, "id");

                cidadeAtual = cidadeService.salvar(cidadeAtual);
                return ResponseEntity.ok(cidadeAtual);
            }
            return ResponseEntity.notFound().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
