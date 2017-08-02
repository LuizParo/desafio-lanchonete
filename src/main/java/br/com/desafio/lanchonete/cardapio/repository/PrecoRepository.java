package br.com.desafio.lanchonete.cardapio.repository;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import java.math.BigDecimal;

public interface PrecoRepository {

    Iterable<Ingrediente> obtemIngredientesComPreco();

    void salvaPrecoParaOIngrediente(String ingrediente, BigDecimal valor);
}