package br.com.desafio.lanchonete.promocao;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class PromocaoMuitoQueijoTest {
    private static final BigDecimal VALOR_DO_QUEIJO = BigDecimal.TEN;
    private static final Ingrediente INGREDIENTE_QUEIJO = new Ingrediente("Queijo", VALOR_DO_QUEIJO);

    private final Promocao promocao = new PromocaoMuitoQueijo();

    @Test
    public void calculaDescontoComValorZeroSeLancheForNull() {
        BigDecimal desconto = this.promocao.calculaDesconto(null);
        assertNotNull(desconto);
        assertEquals(BigDecimal.ZERO, desconto);
    }

    @Test
    public void calculaDescontoComValorZeroSeLanchePossuirMenosDe3PorcoesDeQueijo() {
        Lanche lanche = new Lanche("X-Burger", Arrays.asList(INGREDIENTE_QUEIJO));
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(BigDecimal.ZERO, desconto);
    }

    @Test
    public void calculaDescontoComValor10SeLanchePossuir3PorcoesDeQueijo() {
        Lanche lanche = new Lanche("X-Burger", Arrays.asList(INGREDIENTE_QUEIJO, INGREDIENTE_QUEIJO, INGREDIENTE_QUEIJO));
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(BigDecimal.TEN, desconto);
    }

    @Test
    public void calculaDescontoComValor10SeLanchePossuir5PorcoesDeQueijo() {
        List<Ingrediente> ingredientes = Arrays.asList(INGREDIENTE_QUEIJO, INGREDIENTE_QUEIJO, INGREDIENTE_QUEIJO, INGREDIENTE_QUEIJO, INGREDIENTE_QUEIJO);
        Lanche lanche = new Lanche("X-Burger", ingredientes);
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(BigDecimal.TEN, desconto);
    }

    @Test
    public void calculaDescontoComValor20SeLanchePossuir6PorcoesDeQueijo() {
        List<Ingrediente> ingredientes = Arrays.asList(
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO
        );
        Lanche lanche = new Lanche("X-Burger", ingredientes);
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(new BigDecimal("20"), desconto);
    }

    @Test
    public void calculaDescontoComValor20SeLanchePossuir7PorcoesDeQueijo() {
        List<Ingrediente> ingredientes = Arrays.asList(
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO
        );
        Lanche lanche = new Lanche("X-Burger", ingredientes);
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(new BigDecimal("20"), desconto);
    }

    @Test
    public void calculaDescontoComValor30SeLanchePossuir9PorcoesDeQueijo() {
        List<Ingrediente> ingredientes = Arrays.asList(
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO,
                INGREDIENTE_QUEIJO
        );
        Lanche lanche = new Lanche("X-Burger", ingredientes);
        BigDecimal desconto = this.promocao.calculaDesconto(lanche);

        assertEquals(new BigDecimal("30"), desconto);
    }
}