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
import java.util.Optional;

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
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaid}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaid){

        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaid);
        if (cozinha.isPresent()) {
            return ResponseEntity.ok(cozinha.get());
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
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

        if (cozinhaAtual.isPresent()) {
//        cozinhaAtual.setNome(cozinha.getNome());
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

           Cozinha  cozinhaSalva = cozinhaService.salvar(cozinhaAtual.get());

            return ResponseEntity.ok(cozinhaSalva);
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
