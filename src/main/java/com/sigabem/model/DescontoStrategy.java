package com.sigabem.model;

import java.util.Calendar;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de frete. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 * @author nirlleycosta
 */
abstract class DescontoStrategy {

    abstract void aplicarDesconto(Frete frete);
}
