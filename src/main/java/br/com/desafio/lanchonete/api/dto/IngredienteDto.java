package br.com.desafio.lanchonete.api.dto;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;

public final class IngredienteDto implements Serializable {
    private final String nome;
    private final BigDecimal valor;

    @JsonCreator
    public IngredienteDto(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public static IngredienteDto toDto(Ingrediente ingrediente) {
        return new IngredienteDto(ingrediente.getNome(), ingrediente.getValor());
    }
}