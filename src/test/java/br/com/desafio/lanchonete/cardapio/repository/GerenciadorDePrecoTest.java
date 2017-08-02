package br.com.desafio.lanchonete.cardapio.repository;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import br.com.desafio.lanchonete.cardapio.service.GerenciadorDePreco;
import br.com.desafio.lanchonete.promocao.Promocao;
import br.com.desafio.lanchonete.promocao.PromocaoLight;
import br.com.desafio.lanchonete.promocao.PromocaoMuitaCarne;
import br.com.desafio.lanchonete.promocao.PromocaoMuitoQueijo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GerenciadorDePrecoTest {
    private static final Ingrediente ALFACE = new Ingrediente("Alface", BigDecimal.TEN);
    private static final Ingrediente BACON = new Ingrediente("Bacon", BigDecimal.TEN);
    private static final Ingrediente CARNE_1 = new Ingrediente("Hambúrguer de carne", BigDecimal.TEN);
    private static final Ingrediente CARNE_2 = new Ingrediente("Hambúrguer de carne", BigDecimal.TEN);
    private static final Ingrediente CARNE_3 = new Ingrediente("Hambúrguer de carne", BigDecimal.TEN);

    private GerenciadorDePreco gerenciadorDePreco;
    private Collection<Promocao> promocoes;

    private Lanche lanche;

    @Before
    public void setUp() {
        this.promocoes = Arrays.asList(
                new PromocaoLight(),
                new PromocaoMuitaCarne(),
                new PromocaoMuitoQueijo()
        );

        this.gerenciadorDePreco = new GerenciadorDePreco(this.promocoes);
        this.lanche = new Lanche("X-Bacon", Arrays.asList(ALFACE, BACON, CARNE_1, CARNE_2, CARNE_3));
    }

    @Test
    public void precificaOLancheCorretamente() {
        this.gerenciadorDePreco.precificaOLanche(this.lanche);

        assertEquals(new BigDecimal("40"), this.lanche.getValorComDesconto());
    }
}