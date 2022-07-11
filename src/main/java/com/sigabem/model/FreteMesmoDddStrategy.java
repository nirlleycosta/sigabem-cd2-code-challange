package com.sigabem.model;

import java.util.Calendar;

public class FreteMesmoDddStrategy extends FreteStrategy {
    private final double PORCENTAGEM_DESCONTO = 0.5;
    private final byte PRAZO_ENTREGA_DIAS = 1;

    @Override
    public void calcularValorTotal(Frete frete) {
        double valorComDesconto = aplicarDesconto(frete.getPeso(), PORCENTAGEM_DESCONTO);

        frete.setValorTotalFrete(valorComDesconto);
    }

    @Override
    void definirDataDeEntrega(Frete frete) {
        Calendar previsaoEntrega = preverEntrega(PRAZO_ENTREGA_DIAS);

        frete.setDataPrevistaEntrega(previsaoEntrega);
    }
}
