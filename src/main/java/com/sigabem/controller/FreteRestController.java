package com.sigabem.controller;

import com.sigabem.model.Endereco;
import com.sigabem.model.Frete;
import com.sigabem.repository.EnderecoRepository;
import com.sigabem.repository.FreteRepository;
import com.sigabem.service.ViaCepService;
import feign.FeignException;
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
    public ResponseEntity<?> inserir(@RequestBody FreteRequest freteRequest) {
        Endereco enderecoOrigem = consultarCep(freteRequest.cepOrigem);
        Endereco enderecoDestino = consultarCep(freteRequest.cepDestino);

        if (freteRequest.peso <= 0) {
            return ResponseEntity.badRequest().body("O peso não foi informado");
        }

        if (enderecoOrigem == null) {
            return ResponseEntity.badRequest().body("O CEP de origem não foi encontrado");
        }

        if (enderecoDestino == null) {
            return ResponseEntity.badRequest().body("O CEP de destino não foi encontrado");
        }

        Frete frete = new Frete(freteRequest.peso, freteRequest.nomeDestinatario, enderecoOrigem, enderecoDestino);
        freteRepository.save(frete);

        return ResponseEntity.ok(frete);
    }

    // Não foi implementado a validação do retorno da viaCep para casos de CEP inválido ou inexistente
    private Endereco consultarCep(String cep) {
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
           try {
               Endereco novoEndereco = viaCepService.consultarCep(cep);
               enderecoRepository.save(novoEndereco);
               return novoEndereco;
           } catch (FeignException e) {
               return null;
           }
        });

        return endereco;
    }
}
