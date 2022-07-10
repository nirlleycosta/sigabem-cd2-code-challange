package com.sigabem.service;

import com.sigabem.model.Frete;
import org.springframework.stereotype.Service;

@Service
public interface FreteService {

    Iterable<Frete> buscarTodos();

    Frete buscarPorId(Long id);

    void deletar(Long id);
}
