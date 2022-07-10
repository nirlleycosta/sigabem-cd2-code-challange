package com.sigabem.service;

import com.sigabem.model.Frete;
import org.springframework.stereotype.Service;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de frete. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 * @author nirlleycosta
 */
@Service
public interface DescontoStrategy {

    void inserirDesconto(Frete frete);

}
