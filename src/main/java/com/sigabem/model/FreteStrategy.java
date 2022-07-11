package com.sigabem.model;

import java.util.Calendar;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de frete. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 *
 * @author nirlleycosta
 */
abstract class FreteStrategy {
    abstract void calcularValorTotal(Frete frete);
    abstract void definirDataDeEntrega(Frete frete);

    protected double aplicarDesconto(double valor, double porcentagemDesconto) {
        double desconto = valor * porcentagemDesconto;
        return valor - desconto;
    }

    protected Calendar preverEntrega(byte prazoEntrega) {
        Calendar previsaoEntrega = Calendar.getInstance();
        previsaoEntrega.add(Calendar.DATE, prazoEntrega);
        return previsaoEntrega;
    }
}
