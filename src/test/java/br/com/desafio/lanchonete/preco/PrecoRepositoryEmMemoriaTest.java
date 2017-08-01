package br.com.desafio.lanchonete.preco;

import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrecoRepositoryEmMemoriaTest {
    private final PrecoRepository repository = new PrecoRepositoryEmMemoria();

    @Test
    public void buscaPrecoCorretoQuandoIngredienteExistir() {
        BigDecimal valor = this.repository.buscaPrecoDoIngrediente("Alface");
        assertEquals(new BigDecimal("0.40"), valor);
    }

    @Test
    public void buscaPrecoComoZeroQuandoIngredienteNaoExistir() {
        BigDecimal valor = this.repository.buscaPrecoDoIngrediente("Salsicha");
        assertEquals(BigDecimal.ZERO, valor);
    }

    @Test
    public void buscaPrecoComoZeroQuandoIngredienteForNull() {
        BigDecimal valor = this.repository.buscaPrecoDoIngrediente(null);
        assertEquals(BigDecimal.ZERO, valor);
    }
}