package br.com.desafio.lanchonete.cardapio.api;

import br.com.desafio.lanchonete.cardapio.api.IngredienteDto;
import br.com.desafio.lanchonete.cardapio.api.LancheDto;
import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.Arrays;

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

    @Test
    public void criaEntidadeIngredienteAPartirDoDtoCorretamente() {
        Ingrediente ingrediente = new Ingrediente(NOME_INGREDIENTE, VALOR);
        IngredienteDto dto = IngredienteDto.toDto(ingrediente);

        Ingrediente novoIngrediente = dto.toEntidade();

        assertEquals(NOME_INGREDIENTE, novoIngrediente.getNome());
        assertEquals(VALOR, novoIngrediente.getValor());
    }
}