package com.sigabem.model;

import java.util.Calendar;

public class FretePadraoStrategy extends FreteStrategy {
    private final byte PRAZO_ENTREGA_DIAS = 10;

    @Override
    public void calcularValorTotal(Frete frete) {
        frete.setValorTotalFrete(frete.getPeso());
    }

    @Override
    void definirDataDeEntrega(Frete frete) {
        Calendar previsaoEntrega = preverEntrega(PRAZO_ENTREGA_DIAS);

        frete.setDataPrevistaEntrega(previsaoEntrega);
    }
}
