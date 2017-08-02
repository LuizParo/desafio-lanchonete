package br.com.desafio.lanchonete.preco;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import br.com.desafio.lanchonete.promocao.Promocao;
import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GerenciadorDePreco {
    private final PrecoRepository precoRepository;
    private final Collection<Promocao> promocoes;

    @Autowired
    public GerenciadorDePreco(PrecoRepository precoRepository, Collection<Promocao> promocoes) {
        this.precoRepository = precoRepository;
        this.promocoes = promocoes;
    }

    public void precificaOLanche(Lanche lanche) {
        this.precificaOsIngredientes(lanche.getIngredientes());

        BigDecimal valorTotalDesconto = this.promocoes.stream()
                .map(promocao -> promocao.calculaDesconto(lanche))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        lanche.setDesconto(valorTotalDesconto);
    }

    public void precificaOsIngredientes(Collection<Ingrediente> ingredientes) {
        ingredientes.forEach(ingrediente -> {
            BigDecimal valor = this.precoRepository.obtemPrecoDoIngrediente(ingrediente.getNome());
            ingrediente.setValor(valor);
        });
    }
}
