package com.sigabem.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * FreteRequest implementa o pattern Value Object com os dados deserializados da request.
 *
 * Referência: https://badia-kharroubi.gitbooks.io/microservices-architecture/content/patterns/tactical-patterns/value-object-pattern.html
 */
public class FreteRequest {
    public final double peso;
    public final String cepOrigem;
    public final String cepDestino;
    public final String nomeDestinatario;

    public FreteRequest(
            @JsonProperty("peso") double peso,
            @JsonProperty("cepOrigem") String cepOrigem,
            @JsonProperty("cepDestino") String cepDestino,
            @JsonProperty("nomeDestinatario") String nomeDestinatario) {
        this.peso = peso;
        this.cepOrigem = cepOrigem;
        this.cepDestino = cepDestino;
        this.nomeDestinatario = nomeDestinatario;
    }
}
