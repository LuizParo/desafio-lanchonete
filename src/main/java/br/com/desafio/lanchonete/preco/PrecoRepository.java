package br.com.desafio.lanchonete.preco;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import java.math.BigDecimal;

public interface PrecoRepository {

    BigDecimal obtemPrecoDoIngrediente(String ingrediente);

    Iterable<Ingrediente> obtemIngredientesComPreco();
}