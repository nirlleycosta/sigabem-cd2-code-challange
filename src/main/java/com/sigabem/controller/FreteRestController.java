package com.sigabem.controller;

import com.sigabem.service.DescontoStrategy;
import com.sigabem.model.Frete;
import com.sigabem.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 *
 * @author nirlleycosta
 */
@RestController
@RequestMapping("fretes")
public class FreteRestController {

    @Autowired
    @Qualifier(value = "descontoStrategyDDD")
    private DescontoStrategy descontoStrategy;

    @Autowired
    private FreteService freteService;

    @GetMapping
    public ResponseEntity<Iterable<Frete>> buscarTodos() {
        return ResponseEntity.ok(freteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frete> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(freteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Frete> inserir(@RequestBody Frete frete) {
        descontoStrategy.inserirDesconto(frete);
        return ResponseEntity.ok(frete);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        freteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
