package com.sigabem.model;

import java.util.Calendar;

public class DescontoMesmoEstadoStrategy extends DescontoStrategy {

    private final double VALOR_DESCONTO = 0.75;
    private final byte PRAZO_ENTREGA_DIAS = 3;

    @Override
    public void aplicarDesconto(Frete frete) {
        double desconto = frete.getPeso() * VALOR_DESCONTO;
        double valorComDesconto = frete.getPeso() - desconto;

        Calendar previsaoEntrega = Calendar.getInstance();
        previsaoEntrega.add(Calendar.DATE, PRAZO_ENTREGA_DIAS);

        frete.setValorTotalFrete(valorComDesconto);
        frete.setDataPrevistaEntrega(previsaoEntrega);
    }
}
