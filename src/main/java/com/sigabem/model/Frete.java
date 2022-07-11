package com.sigabem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "frete_id")
    private Long id;

    @Column(name = "peso")
    private double peso;

    @Column(name = "cep_origem")
    @JsonProperty("cepOrigem")
    private String cepOrigem;

    @Column(name = "cep_destino")
    @JsonProperty("cepDestino")
    private String cepDestino;

    @Column(name = "nome_destinatario")
    private String nomeDestinatario;

    @Column(name = "valortotal")
    @JsonProperty("vlTotalFrete")
    private double valorTotalFrete;

    @Column(name = "previsao_entrega")
    @JsonProperty("dataPrevistaEntrega")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Calendar dataPrevistaEntrega;

    @Column(name = "data_consulta")
    @CreationTimestamp
    private Timestamp dataConsulta;

    @ManyToOne
    private Endereco enderecoOrigem;

    @ManyToOne
    private Endereco enderecoDestino;

    @Transient
    private FreteStrategy freteStrategy = new FretePadraoStrategy();

    private Frete() {
    }

    public Frete(double peso, String nomeDestinatario, Endereco enderecoOrigem, Endereco enderecoDestino) {
        this.peso = peso;
        this.nomeDestinatario = nomeDestinatario;
        this.enderecoOrigem = enderecoOrigem;
        this.enderecoDestino = enderecoDestino;

        this.cepOrigem = enderecoOrigem.getCep();
        this.cepDestino = enderecoDestino.getCep();

        if (isMesmoDdd()) {
            freteStrategy = new FreteMesmoDddStrategy();
        } else if (isMesmoEstado()) {
            freteStrategy = new FreteMesmoEstadoStrategy();
        }

        freteStrategy.calcularValorTotal(this);
        freteStrategy.definirDataDeEntrega(this);
    }

    public double getPeso() {
        return peso;
    }

    void setValorTotalFrete(double valorTotalFrete) {
        this.valorTotalFrete = valorTotalFrete;
    }

    void setDataPrevistaEntrega(Calendar dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
    }

    private boolean isMesmoDdd() {
        return enderecoOrigem.getDdd().equals(enderecoDestino.getDdd());
    }

    private boolean isMesmoEstado() {
        return enderecoOrigem.getUf().equals(enderecoDestino.getUf());
    }
}
