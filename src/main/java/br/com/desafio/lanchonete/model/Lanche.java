package br.com.desafio.lanchonete.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

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

    public long getQuantidadeDeAlface() {
        return this.ingredientes.stream()
            .filter(ingrediente -> ingrediente.getNome().toLowerCase().contains("alface"))
            .count();
    }

    public long getQuantidadeDeBacon() {
        return this.ingredientes.stream()
                .filter(ingrediente -> ingrediente.getNome().toLowerCase().contains("bacon"))
                .count();
    }

    public long getQuantidadeDeCarne() {
        return this.ingredientes.stream()
                .filter(ingrediente -> ingrediente.getNome().toLowerCase().contains("carne"))
                .count();
    }

    public long getQuantidadeDeOvo() {
        return this.ingredientes.stream()
            .filter(ingrediente -> ingrediente.getNome().toLowerCase().contains("ovo"))
            .count();
    }

    public long getQuantidadeDeQueijo() {
        return this.ingredientes.stream()
            .filter(ingrediente -> ingrediente.getNome().toLowerCase().contains("queijo"))
            .count();
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