package br.com.desafio.lanchonete.cardapio.repository;

import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"test", "default"})
public class CardapioRepositoryEmMemoria implements CardapioRepository {
    private static final Map<String, Lanche> CACHE = new HashMap<>();

    @Override
    public Iterable<Lanche> obtemLanchesProntosDoCardapio() {
        return CACHE.values();
    }

    @Override
    public void salvaLancheNoCardapio(Lanche lanche) {
        CACHE.put(lanche.getNome(), lanche);
    }
}