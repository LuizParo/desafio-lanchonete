package br.com.desafio.lanchonete.promocao;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class PromocaoMuitaCarneTest {
    private static final BigDecimal VALOR_DA_CARNE = BigDecimal.TEN;
    private static final Ingrediente INGREDIENTE_CARNE = new Ingrediente("Hambúrguer de carne", VALOR_DA_CARNE);

    private final Promocao promocao = new PromocaoMuitaCarne();

    @Test
    public void calculaDescontoComValorZeroSeLancheForNull() {
        BigDecimal desconto = this.promocao.calculaDesconto(null);
        assertNotNull(desconto);
        assertEquals(BigDecimal.ZERO, desconto);
    }

    @Test
    public void calculaDescontoComValorZeroSeLanchePossuirMenosDe3PorcoesDeCarne() {
        Lanche lanche = new Lanche("X-Burger", Arrays.asList(INGREDIENTE_CARNE));
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(BigDecimal.ZERO, desconto);
    }

    @Test
    public void calculaDescontoComValor10SeLanchePossuir3PorcoesDeCarne() {
        Lanche lanche = new Lanche("X-Burger", Arrays.asList(INGREDIENTE_CARNE, INGREDIENTE_CARNE, INGREDIENTE_CARNE));
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(BigDecimal.TEN, desconto);
    }

    @Test
    public void calculaDescontoComValor10SeLanchePossuir5PorcoesDeCarne() {
        List<Ingrediente> ingredientes = Arrays.asList(INGREDIENTE_CARNE, INGREDIENTE_CARNE, INGREDIENTE_CARNE, INGREDIENTE_CARNE, INGREDIENTE_CARNE);
        Lanche lanche = new Lanche("X-Burger", ingredientes);
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(BigDecimal.TEN, desconto);
    }

    @Test
    public void calculaDescontoComValor20SeLanchePossuir6PorcoesDeCarne() {
        List<Ingrediente> ingredientes = Arrays.asList(
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE
        );
        Lanche lanche = new Lanche("X-Burger", ingredientes);
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(new BigDecimal("20"), desconto);
    }

    @Test
    public void calculaDescontoComValor20SeLanchePossuir7PorcoesDeCarne() {
        List<Ingrediente> ingredientes = Arrays.asList(
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE
        );
        Lanche lanche = new Lanche("X-Burger", ingredientes);
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(new BigDecimal("20"), desconto);
    }

    @Test
    public void calculaDescontoComValor30SeLanchePossuir9PorcoesDeCarne() {
        List<Ingrediente> ingredientes = Arrays.asList(
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE,
                INGREDIENTE_CARNE
        );
        Lanche lanche = new Lanche("X-Burger", ingredientes);
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(new BigDecimal("30"), desconto);
    }
}