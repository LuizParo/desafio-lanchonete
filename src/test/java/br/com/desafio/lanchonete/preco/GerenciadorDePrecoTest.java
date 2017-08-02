package br.com.desafio.lanchonete.preco;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
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
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GerenciadorDePrecoTest {
    private static final Ingrediente ALFACE = new Ingrediente("Alface", BigDecimal.ZERO);
    private static final Ingrediente BACON = new Ingrediente("Bacon", BigDecimal.ZERO);
    private static final Ingrediente CARNE_1 = new Ingrediente("Hambúrguer de carne", BigDecimal.ZERO);
    private static final Ingrediente CARNE_2 = new Ingrediente("Hambúrguer de carne", BigDecimal.ZERO);
    private static final Ingrediente CARNE_3 = new Ingrediente("Hambúrguer de carne", BigDecimal.ZERO);

    private GerenciadorDePreco gerenciadorDePreco;

    @Mock
    private PrecoRepository precoRepository;

    @Mock
    private Collection<Promocao> promocoes;

    private Lanche lanche;

    @Before
    public void setUp() {
        this.promocoes = Arrays.asList(
                new PromocaoLight(),
                new PromocaoMuitaCarne(),
                new PromocaoMuitoQueijo()
        );

        this.gerenciadorDePreco = new GerenciadorDePreco(this.precoRepository, this.promocoes);
        this.lanche = new Lanche("X-Bacon", Arrays.asList(ALFACE, BACON, CARNE_1, CARNE_2, CARNE_3));
    }

    @Test
    public void precificaOsIngredientesDoLancheCorretamente() {
        when(this.precoRepository.obtemPrecoDoIngrediente(anyString())).thenReturn(BigDecimal.TEN);
        this.gerenciadorDePreco.precificaOsIngredientes(this.lanche.getIngredientes());

        assertEquals(BigDecimal.TEN, ALFACE.getValor());
        assertEquals(BigDecimal.TEN, BACON.getValor());
        assertEquals(BigDecimal.TEN, CARNE_1.getValor());
        assertEquals(BigDecimal.TEN, CARNE_2.getValor());
        assertEquals(BigDecimal.TEN, CARNE_3.getValor());
    }

    @Test
    public void precificaOLancheCorretamente() {
        when(this.precoRepository.obtemPrecoDoIngrediente(anyString())).thenReturn(BigDecimal.TEN);
        this.gerenciadorDePreco.precificaOLanche(this.lanche);

        assertEquals(new BigDecimal("40"), this.lanche.getValorTotal());
    }
}