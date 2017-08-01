package br.com.desafio.lanchonete.model;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public final class Ingrediente {
    private final String nome;
    private final BigDecimal valor;

    public Ingrediente(String nome, BigDecimal valor) {
        this.nome = isNull(nome) ? "" : nome;
        this.valor = isNull(valor) ? BigDecimal.ZERO : valor;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Ingrediente)) return false;

        Ingrediente that = (Ingrediente) o;

        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}