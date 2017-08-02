package br.com.desafio.lanchonete.promocao;

import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;

public interface Promocao {

    BigDecimal calculaDesconto(Lanche lanche);
}