package br.com.desafio.lanchonete.cardapio.service;

import br.com.desafio.lanchonete.cardapio.api.IngredienteDto;
import br.com.desafio.lanchonete.cardapio.model.Ingrediente;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IngredienteServiceIT {
    private static final Ingrediente ALFACE = new Ingrediente("Alface", new BigDecimal("0.40"));
    private static final Ingrediente BACON = new Ingrediente("Bacon", new BigDecimal("2.00"));
    private static final Ingrediente CARNE = new Ingrediente("Hambúrguer de carne", new BigDecimal("3.00"));
    private static final Ingrediente OVO = new Ingrediente("Ovo", new BigDecimal("0.80"));
    private static final Ingrediente QUEIJO = new Ingrediente("Queijo", new BigDecimal("1.50"));

    @Autowired
    private IngredienteService ingredienteService;

    @Test
    public void obtemIngredientesComPrecoConvertidoEmDto() {
        List<Ingrediente> ingredientes = Arrays.asList(
                ALFACE,
                BACON,
                CARNE,
                OVO,
                QUEIJO
        );

        Iterable<IngredienteDto> dtos = this.ingredienteService.obtemIngredientesComPreco();
        assertEquals(ingredientes.size(), ((Collection<IngredienteDto>)dtos).size());
    }
}