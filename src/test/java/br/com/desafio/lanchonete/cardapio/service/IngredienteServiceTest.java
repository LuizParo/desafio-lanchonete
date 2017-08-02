package br.com.desafio.lanchonete.cardapio.service;

import br.com.desafio.lanchonete.cardapio.api.IngredienteDto;
import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.repository.PrecoRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredienteServiceTest {
    private static final Ingrediente ALFACE = new Ingrediente("Alface", new BigDecimal("0.40"));
    private static final Ingrediente BACON = new Ingrediente("Bacon", new BigDecimal("2.00"));
    private static final Ingrediente CARNE = new Ingrediente("Hambúrguer de carne", new BigDecimal("3.00"));
    private static final Ingrediente OVO = new Ingrediente("Ovo", new BigDecimal("0.80"));
    private static final Ingrediente QUEIJO = new Ingrediente("Queijo", new BigDecimal("1.50"));

    @InjectMocks
    private IngredienteService ingredienteService;

    @Mock
    private PrecoRepository precoRepository;

    @Test
    public void obtemIngredientesComPrecoConvertidoEmDto() {
        List<Ingrediente> ingredientes = Arrays.asList(
                ALFACE,
                BACON,
                CARNE,
                OVO,
                QUEIJO
        );

        when(this.precoRepository.obtemIngredientesComPreco()).thenReturn(ingredientes);

        Iterable<IngredienteDto> dtos = this.ingredienteService.obtemIngredientesComPreco();
        assertEquals(ingredientes.size(), ((Collection<IngredienteDto>)dtos).size());

        Iterator<IngredienteDto> ingredientesRetornados = dtos.iterator();
        this.comparaIngredientes(ALFACE, ingredientesRetornados.next());
        this.comparaIngredientes(BACON, ingredientesRetornados.next());
        this.comparaIngredientes(CARNE, ingredientesRetornados.next());
        this.comparaIngredientes(OVO, ingredientesRetornados.next());
        this.comparaIngredientes(QUEIJO, ingredientesRetornados.next());
    }

    private void comparaIngredientes(Ingrediente ingrediente, IngredienteDto dto) {
        assertEquals(ingrediente.getNome(), dto.getNome());
        assertEquals(ingrediente.getValor(), dto.getValor());
    }
}