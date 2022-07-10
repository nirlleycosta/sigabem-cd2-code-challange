package com.sigabem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "frete_id")
    private Long id;

    @Column(name = "peso")
    private double peso;

    @Column(name = "cep_origem")
    private String cepOrigem;

    @Column(name = "cep_destino")
    private String cepDestino;

    @Column(name = "nome_destinatario")
    private String nomeDestinatario;

    @Column(name = "valortotal")
    private double valorTotalFrete;

    @Column(name = "previsao_entrega")
    private String dataPrevistaEntrega;
    // Como agregarei os dias de acordo com o cep?

    @Column(name = "data_consulta")
    private String dataConsulta;

    @ManyToOne
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCepOrigem() {
        return cepOrigem;
    }

    public void setCepOrigem(String cepOrigem) {
        this.cepOrigem = cepOrigem;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public double getValorTotalFrete() {
        return valorTotalFrete;
    }

    public void setValorTotalFrete(double valorTotalFrete) {
        this.valorTotalFrete = valorTotalFrete;
    }

    public String getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

    public void setDataPrevistaEntrega(String dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
