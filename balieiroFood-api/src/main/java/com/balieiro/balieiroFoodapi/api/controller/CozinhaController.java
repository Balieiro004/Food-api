package com.balieiro.balieiroFoodapi.api.controller;

import com.balieiro.balieiroFoodapi.domain.entity.Cozinha;
import com.balieiro.balieiroFoodapi.domain.exception.EntidadeEmUsoException;
import com.balieiro.balieiroFoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.balieiro.balieiroFoodapi.domain.service.CadastroCozinhaService;
import com.balieiro.balieiroFoodapi.infraestructure.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;
    private CadastroCozinhaService cozinhaService;

    public CozinhaController(CozinhaRepository cozinhaRepository, CadastroCozinhaService cozinhaService) {
        this.cozinhaRepository = cozinhaRepository;
        this.cozinhaService = cozinhaService;
    }

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.listar();
    }

    @GetMapping("/{cozinhaid}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaid){

        Cozinha cozinha = cozinhaRepository.buscar(cozinhaid);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);

        if (cozinhaAtual != null) {
//        cozinhaAtual.setNome(cozinha.getNome());
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

            cozinhaAtual = cozinhaService.salvar(cozinhaAtual);

            return ResponseEntity.ok(cozinhaAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId){
        try {
            cozinhaService.excluir(cozinhaId);
            return ResponseEntity.noContent().build();

        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();

        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
