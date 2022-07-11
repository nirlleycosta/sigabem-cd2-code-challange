package com.sigabem.model;

import java.util.Calendar;

public class DescontoPadraoStrategy extends DescontoStrategy {
    private final byte PRAZO_ENTREGA_DIAS = 10;

    @Override
    public void aplicarDesconto(Frete frete) {
        Calendar previsaoEntrega = Calendar.getInstance();
        previsaoEntrega.add(Calendar.DATE, PRAZO_ENTREGA_DIAS);

        frete.setValorTotalFrete(frete.getPeso());
        frete.setDataPrevistaEntrega(previsaoEntrega);
    }
}
