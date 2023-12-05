package com.balieiro.balieiroFoodapi.api.controller;

import com.balieiro.balieiroFoodapi.domain.entity.Restaurante;
import com.balieiro.balieiroFoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.balieiro.balieiroFoodapi.domain.service.CadastroRestauranteService;
import com.balieiro.balieiroFoodapi.infraestructure.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private RestauranteRepository restauranteRepository;

    private CadastroRestauranteService restauranteService;

    public RestauranteController(RestauranteRepository restauranteRepository, CadastroRestauranteService restauranteService) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }
    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId){

        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
        if (restaurante.isPresent()){
            return ResponseEntity.ok(restaurante.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/por-nome")
    public List<Restaurante> contultaPorNome(String nome, Long cozinhaId){
        return restauranteRepository.contultaPorNome(nome, cozinhaId);
    }
    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
        try{
            restaurante = restauranteService.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
        try {
            if (restauranteAtual.isPresent()) {
                BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

                Restaurante restauranteSalva = restauranteService.salvar(restauranteAtual.get());

                return ResponseEntity.ok(restauranteSalva);
            }

            return ResponseEntity.notFound().build();

        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos){
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

        if(restauranteAtual.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        merge(campos, restauranteAtual.get());

        return atualizar(restauranteId, restauranteAtual.get());
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino){
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}
