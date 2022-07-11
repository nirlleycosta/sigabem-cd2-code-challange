package com.sigabem.controller;

import com.sigabem.model.Endereco;
import com.sigabem.model.Frete;
import com.sigabem.repository.EnderecoRepository;
import com.sigabem.repository.FreteRepository;
import com.sigabem.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private FreteRepository freteRepository;

    @PostMapping
    public ResponseEntity<Frete> inserir(@RequestBody FreteRequest freteRequest) {
        Endereco enderecoOrigem = consultarCep(freteRequest.cepOrigem);
        Endereco enderecoDestino = consultarCep(freteRequest.cepDestino);

        Frete frete = new Frete(freteRequest.peso, freteRequest.nomeDestinatario, enderecoOrigem, enderecoDestino);
        freteRepository.save(frete);

        return ResponseEntity.ok(frete);
    }

    private Endereco consultarCep(String cep) {
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        return endereco;
    }
}
