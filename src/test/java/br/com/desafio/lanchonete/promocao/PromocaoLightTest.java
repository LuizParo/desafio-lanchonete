package br.com.desafio.lanchonete.promocao;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class PromocaoLightTest {
    private final Promocao promocao = new PromocaoLight();

    @Test
    public void calculaDescontoComValorZeroSeLancheForNull() {
        BigDecimal desconto = this.promocao.calculaDesconto(null);
        assertNotNull(desconto);
        assertEquals(BigDecimal.ZERO, desconto);
    }

    @Test
    public void lancheNaoPossuiDescontoSeNaoPossuirAlface() {
        List<Ingrediente> ingredientes = Arrays.asList(new Ingrediente("Ovo", BigDecimal.TEN));
        Lanche lanche = new Lanche("X-Egg", ingredientes);

        BigDecimal desconto = this.promocao.calculaDesconto(lanche);
        assertEquals(BigDecimal.ZERO, desconto);
    }

    @Test
    public void lancheNaoPossuiDescontoSePossuirBacon() {
        List<Ingrediente> ingredientes = Arrays.asList(new Ingrediente("Bacon", BigDecimal.TEN));
        Lanche lanche = new Lanche("X-Egg", ingredientes);

        BigDecimal desconto = this.promocao.calculaDesconto(lanche);
        assertEquals(BigDecimal.ZERO, desconto);
    }

    @Test
    public void lancheNaoPossuiDescontoSePossuirAlfaceEBacon() {
        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Alface", BigDecimal.TEN),
                new Ingrediente("Bacon", BigDecimal.TEN)
        );
        Lanche lanche = new Lanche("X-Egg", ingredientes);

        BigDecimal desconto = this.promocao.calculaDesconto(lanche);
        assertEquals(BigDecimal.ZERO, desconto);
    }

    @Test
    public void lanchePossuiDescontoSePossuirAlface() {
        List<Ingrediente> ingredientes = Arrays.asList(new Ingrediente("Alface", BigDecimal.TEN));
        Lanche lanche = new Lanche("X-Egg", ingredientes);

        BigDecimal desconto = this.promocao.calculaDesconto(lanche);
        assertEquals(BigDecimal.ONE.setScale(2), desconto);
    }
}