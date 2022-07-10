package com.sigabem.service.impl;

import com.sigabem.model.Endereco;
import com.sigabem.repository.EnderecoRepository;
import com.sigabem.model.Frete;
import com.sigabem.repository.FreteRepository;
import com.sigabem.service.DescontoStrategy;
import com.sigabem.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação da <b>Strategy</b> {@link DescontoStrategy}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}).
 * Com isso, como essa classe é um {@link Service}, ela será tratada como um <b>Singleton</b>
 *
 * @author nirlleycosta
 */

@Service
public class DescontoStrategyDDD implements DescontoStrategy {

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public void inserirDesconto(Frete frete) {
        aplicarCalculo(frete);
    }

    private void aplicarCalculo(Frete frete) {
        // FIXME: Se CEP's tiverem o mesmo DDD
        //  aplicar 50% desconto no valor total
        //  aplicar 1 dia de previsão de entrega

        // Integrar com o ViaCEP e persistir o retorno.
        String cep = frete.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        frete.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereço (novo ou existente)
        freteRepository.save(frete);
    }

}

