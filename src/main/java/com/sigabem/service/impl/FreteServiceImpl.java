package com.sigabem.service.impl;

import com.sigabem.model.Frete;
import com.sigabem.repository.FreteRepository;
import com.sigabem.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link FreteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}).
 * Com isso, como essa classe é um {@link Service}, ela será tratada como um <b>Singleton</b>
 *
 * @author nirlleycosta
 */
@Service
public class FreteServiceImpl implements FreteService {

    // TODO Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private FreteRepository freteRepository;

    @Override
    public Iterable<Frete> buscarTodos() {
        return freteRepository.findAll();
    }

    @Override
    public Frete buscarPorId(Long id) {
        Optional<Frete> frete = freteRepository.findById(id);
        return frete.get();
    }

    @Override
    public void deletar(Long id) {
        freteRepository.deleteById(id);
    }
}
