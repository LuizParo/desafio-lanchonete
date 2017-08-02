package br.com.desafio.lanchonete.cardapio.repository;

import br.com.desafio.lanchonete.cardapio.model.Lanche;

public interface CardapioRepository {

    Iterable<Lanche> obtemLanchesProntosDoCardapio();

    void salvaLancheNoCardapio(Lanche lanche);
}