package br.com.desafio.lanchonete.cardapio.api;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.isNull;

public final class LancheDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nome;
    private final BigDecimal valorTotal;
    private final BigDecimal desconto;
    private final Collection<IngredienteDto> ingredientes;

    @JsonCreator
    public LancheDto(@JsonProperty("nome") String nome,
                     @JsonProperty("valorTotal") BigDecimal valorTotal,
                     @JsonProperty("desconto") BigDecimal desconto,
                     @JsonProperty("ingredientes") Collection<IngredienteDto> ingredientes) {
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.ingredientes = isNull(ingredientes) ? new ArrayList<>() : ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public Collection<IngredienteDto> getIngredientes() {
        return ingredientes;
    }

    public Lanche toEntidade() {
        List<Ingrediente> ingredientes = this.ingredientes.stream()
                .map(IngredienteDto::toEntidade)
                .collect(Collectors.toList());

        Lanche lanche = new Lanche(this.nome, ingredientes);
        lanche.setDesconto(this.desconto);

        return lanche;
    }

    public static LancheDto toDto(Lanche lanche) {
        List<IngredienteDto> ingredientes = lanche.getIngredientes().stream()
                .map(IngredienteDto::toDto)
                .collect(Collectors.toList());

        return new LancheDto(lanche.getNome(), lanche.getValorTotal(), lanche.getDesconto(), ingredientes);
    }
}
