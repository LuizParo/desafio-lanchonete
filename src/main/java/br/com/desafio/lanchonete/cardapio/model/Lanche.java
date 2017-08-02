package br.com.desafio.lanchonete.cardapio.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public final class Lanche {
    private final String nome;
    private final Collection<Ingrediente> ingredientes;
    private BigDecimal desconto = BigDecimal.ZERO;

    public Lanche(String nome, Collection<Ingrediente> ingredientes) {
        this.nome = isNull(nome) ? "" : nome;
        this.ingredientes = isNull(ingredientes) ? new HashSet<>() : ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = isNull(desconto) ? BigDecimal.ZERO : desconto;
    }

    public Collection<Ingrediente> getIngredientes() {
        return new ArrayList<>(this.ingredientes);
    }

    public BigDecimal getValorTotal() {
        return this.ingredientes.stream()
            .map(Ingrediente::getValor)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getValorComDesconto() {
        return this.getValorTotal().subtract(this.desconto);
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
        return this.getCarnes().count();
    }

    public long getQuantidadeDeOvo() {
        return this.ingredientes.stream()
            .filter(ingrediente -> ingrediente.getNome().toLowerCase().contains("ovo"))
            .count();
    }

    public long getQuantidadeDeQueijo() {
        return this.getQueijos()
            .count();
    }

    public BigDecimal getValorIndividualDaCarne() {
        if(this.getQuantidadeDeCarne() == 0) {
            return BigDecimal.ZERO;
        }

        return this.getCarnes()
            .map(Ingrediente::getValor)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(BigDecimal.valueOf(this.getQuantidadeDeCarne()), RoundingMode.HALF_EVEN);
    }

    public BigDecimal getValorIndividualDoQueijo() {
        if(this.getQuantidadeDeQueijo() == 0) {
            return BigDecimal.ZERO;
        }

        return this.getQueijos()
            .map(Ingrediente::getValor)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(BigDecimal.valueOf(this.getQuantidadeDeQueijo()), RoundingMode.HALF_EVEN);
    }

    private Stream<Ingrediente> getCarnes() {
        return ingredientes.stream()
            .filter(ingrediente -> ingrediente.getNome().toLowerCase().contains("carne"));
    }

    private Stream<Ingrediente> getQueijos() {
        return ingredientes.stream()
            .filter(ingrediente -> ingrediente.getNome().toLowerCase().contains("queijo"));
    }
}