package br.com.desafio.lanchonete.cardapio.repository;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;

public interface PrecoRepository {

    Iterable<Ingrediente> obtemIngredientesComPreco();
}