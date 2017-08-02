package br.com.desafio.lanchonete.cardapio.model;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredienteTest {

    @Test
    public void criaIngredienteComNomeVazioSeNullForPassadoComoParametroParaEste() {
        Ingrediente ingrediente = new Ingrediente(null, BigDecimal.ZERO);
        assertNotNull(ingrediente.getNome());
        assertEquals("", ingrediente.getNome());
    }

    @Test
    public void criaIngredienteComValorZeroSeNullForPassadoComoParametroParaEste() {
        Ingrediente ingrediente = new Ingrediente("Bacon", null);
        assertNotNull(ingrediente.getValor());
        assertEquals(BigDecimal.ZERO, ingrediente.getValor());
    }
}