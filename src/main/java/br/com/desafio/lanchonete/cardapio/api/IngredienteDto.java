package br.com.desafio.lanchonete.cardapio.api;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class IngredienteDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nome;
    private final BigDecimal valor;

    @JsonCreator
    public IngredienteDto(@JsonProperty("nome") String nome, @JsonProperty("valor") BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Ingrediente toEntidade() {
        return new Ingrediente(this.nome, this.valor);
    }

    public static IngredienteDto toDto(Ingrediente ingrediente) {
        return new IngredienteDto(ingrediente.getNome(), ingrediente.getValor());
    }
}