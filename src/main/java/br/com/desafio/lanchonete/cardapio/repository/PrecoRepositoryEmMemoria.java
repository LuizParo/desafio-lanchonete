package br.com.desafio.lanchonete.cardapio.repository;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"test", "default"})
public class PrecoRepositoryEmMemoria implements PrecoRepository {
    private static final Map<String, BigDecimal> CACHE = new HashMap<>();

    @Override
    public Iterable<Ingrediente> obtemIngredientesComPreco() {
        return this.CACHE.entrySet()
            .stream()
            .map(entry -> new Ingrediente(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
    }

    @Override
    public void salvaPrecoParaOIngrediente(String ingrediente, BigDecimal valor) {
        CACHE.put(ingrediente, valor);
    }
}