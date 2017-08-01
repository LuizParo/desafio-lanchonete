package br.com.desafio.lanchonete.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class LancheTest {

    @Test
    public void criaLancheComNomeVazioSeNullForPasadoComoParametroParaEste() {
        Lanche lanche = new Lanche(null, new ArrayList<>());
        assertNotNull(lanche.getNome());
        assertEquals("", lanche.getNome());
    }

    @Test
    public void criaLancheComSemIngredientesSeNullForPasadoComoParametroParaEste() {
        Lanche lanche = new Lanche("X-Bacon", null);
        assertNotNull(lanche.getIngredientes());
        assertTrue(lanche.getIngredientes().isEmpty());
    }

    @Test
    public void calculaValorTotalDoLancheComoZeroSeNenhumIngredienteEstiverPreente() {
        Lanche lanche = new Lanche("X-Bacon", new HashSet<>());
        assertEquals(BigDecimal.ZERO, lanche.getValor());
    }

    @Test
    public void calculaValorTotalDoLancheSomandoOValorDeTodosOsIngredientes() {
        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Alface", new BigDecimal("0.40")),
                new Ingrediente("Bacon", new BigDecimal("2.00")),
                new Ingrediente("Hambúrguer de carne", new BigDecimal("3.00")),
                new Ingrediente("Ovo", new BigDecimal("0.80")),
                new Ingrediente("Queijo", new BigDecimal("1.50"))
        );

        Lanche lanche = new Lanche("X-Tudo", ingredientes);
        assertEquals(new BigDecimal("7.70"), lanche.getValor());
    }
}