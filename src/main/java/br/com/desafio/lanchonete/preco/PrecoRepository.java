package br.com.desafio.lanchonete.preco;

import java.math.BigDecimal;

public interface PrecoRepository {

    BigDecimal buscaPrecoDoIngrediente(String ingrediente);
}