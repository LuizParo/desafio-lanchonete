package br.com.desafio.lanchonete.cardapio.service;

import br.com.desafio.lanchonete.cardapio.model.Lanche;
import br.com.desafio.lanchonete.promocao.Promocao;
import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GerenciadorDePreco {
    private final Collection<Promocao> promocoes;

    @Autowired
    public GerenciadorDePreco(Collection<Promocao> promocoes) {
        this.promocoes = promocoes;
    }

    public void precificaOLanche(Lanche lanche) {
        BigDecimal valorTotalDesconto = this.promocoes.stream()
                .map(promocao -> promocao.calculaDesconto(lanche))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        lanche.setDesconto(valorTotalDesconto);
    }
}
