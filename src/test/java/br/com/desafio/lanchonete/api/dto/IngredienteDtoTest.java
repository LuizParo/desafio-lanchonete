package br.com.desafio.lanchonete.api.dto;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredienteDtoTest {
    private static final String NOME_INGREDIENTE = "Ovo";
    private static final BigDecimal VALOR = BigDecimal.TEN;

    @Test
    public void criaDtoDeIngredienteEspelhandoEntidadeIngrediente() {
        Ingrediente ingrediente = new Ingrediente(NOME_INGREDIENTE, VALOR);
        IngredienteDto dto = IngredienteDto.toDto(ingrediente);

        assertEquals(NOME_INGREDIENTE, dto.getNome());
        assertEquals(VALOR, dto.getValor());
    }
}