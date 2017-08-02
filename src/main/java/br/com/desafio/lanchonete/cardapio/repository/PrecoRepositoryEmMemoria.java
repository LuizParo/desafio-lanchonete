package br.com.desafio.lanchonete.cardapio.repository;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import static java.util.Objects.isNull;

@Repository
public class PrecoRepositoryEmMemoria implements PrecoRepository {
    private static final Map<String, BigDecimal> CACHE = new HashMap<>();

    static {
        CACHE.put("Alface", new BigDecimal("0.40"));
        CACHE.put("Bacon", new BigDecimal("2.00"));
        CACHE.put("Hambúrguer de carne", new BigDecimal("3.00"));
        CACHE.put("Ovo", new BigDecimal("0.80"));
        CACHE.put("Queijo", new BigDecimal("1.50"));
    }

    @Override
    public Iterable<Ingrediente> obtemIngredientesComPreco() {
        return this.CACHE.entrySet()
            .stream()
            .map(entry -> new Ingrediente(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }
}