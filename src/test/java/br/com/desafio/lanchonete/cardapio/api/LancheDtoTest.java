package br.com.desafio.lanchonete.cardapio.api;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class LancheDtoTest {
    private static final String NOME_LANCHE = "X-Burger";
    private static final Ingrediente INGREDIENTE = new Ingrediente("Queijo", BigDecimal.TEN);

    @Test
    public void criaDtoDeLancheEspelhandoEntidadeLanche() {
        Lanche lanche = new Lanche(NOME_LANCHE, Arrays.asList(INGREDIENTE));
        lanche.setDesconto(BigDecimal.ONE);

        LancheDto dto = LancheDto.toDto(lanche);
        assertEquals(NOME_LANCHE, dto.getNome());
        assertEquals(BigDecimal.TEN, dto.getValorTotal());
        assertEquals(BigDecimal.ONE, dto.getDesconto());
        assertNotNull(dto.getIngredientes());
        assertFalse(dto.getIngredientes().isEmpty());
        assertEquals(1, dto.getIngredientes().size());
    }

    @Test
    public void criaEntidadeLancheAPartirDoDtoCorretamente() {
        Lanche lanche = new Lanche(NOME_LANCHE, Arrays.asList(INGREDIENTE));
        lanche.setDesconto(BigDecimal.ONE);
        LancheDto dto = LancheDto.toDto(lanche);

        Lanche novoLanche = dto.toEntidade();

        assertEquals(NOME_LANCHE, novoLanche.getNome());
        assertEquals(BigDecimal.TEN, novoLanche.getValorTotal());
        assertEquals(BigDecimal.ONE, novoLanche.getDesconto());
        assertNotNull(novoLanche.getIngredientes());
        assertFalse(novoLanche.getIngredientes().isEmpty());
        assertEquals(1, novoLanche.getIngredientes().size());
    }
}