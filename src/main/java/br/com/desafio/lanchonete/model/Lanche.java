package br.com.desafio.lanchonete.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

import static java.util.Objects.isNull;

public final class Lanche {
    private final String nome;
    private final Collection<Ingrediente> ingredientes;

    public Lanche(String nome, Collection<Ingrediente> ingredientes) {
        this.nome = isNull(nome) ? "" : nome;
        this.ingredientes = isNull(ingredientes) ? new HashSet<>() : ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public Collection<Ingrediente> getIngredientes() {
        return new HashSet<>(this.ingredientes);
    }

    public BigDecimal getValor() {
        return this.ingredientes.stream()
            .map(Ingrediente::getValor)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Lanche)) return false;

        Lanche lanche = (Lanche) o;

        return nome.equals(lanche.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}