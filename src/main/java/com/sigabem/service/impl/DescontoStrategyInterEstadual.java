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
public class DescontoStrategyInterEstadual implements DescontoStrategy {

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
        // FIXME: Se CEP's tiverem UF diferentes
        //  não aplicar  desconto no valor total
        //  aplicar 10 dias de previsão de entrega

        String cep = frete.getEndereco().getCep();
        Endereco endereco = viaCepService.consultarCep(cep);
        enderecoRepository.save(endereco);
    }
}
