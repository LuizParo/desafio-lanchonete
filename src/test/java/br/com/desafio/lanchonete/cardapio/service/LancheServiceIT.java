package br.com.desafio.lanchonete.cardapio.service;

import br.com.desafio.lanchonete.cardapio.api.LancheDto;
import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LancheServiceIT {

    @Autowired
    private LancheService lancheService;

    private LancheDto lancheDto;

    @Before
    public void setUp() {
        Ingrediente alface = new Ingrediente("Alface", BigDecimal.TEN);
        Ingrediente queijo1 = new Ingrediente("Queijo", BigDecimal.TEN);
        Ingrediente queijo2 = new Ingrediente("Queijo", BigDecimal.TEN);
        Ingrediente queijo3 = new Ingrediente("Queijo", BigDecimal.TEN);
        Ingrediente carne1 = new Ingrediente("Hambúrguer de carne", BigDecimal.TEN);
        Ingrediente carne2 = new Ingrediente("Hambúrguer de carne", BigDecimal.TEN);
        Ingrediente carne3 = new Ingrediente("Hambúrguer de carne", BigDecimal.TEN);

        Lanche lanche = new Lanche("X-Burger", Arrays.asList(alface, queijo1, queijo2, queijo3, carne1, carne2, carne3));
        this.lancheDto = LancheDto.toDto(lanche);
    }

    @Test
    public void precificaOLancheLevandoEmConsideracaoOsDescontosDePromocao() {
        LancheDto novoLanche = this.lancheService.criaLanche(this.lancheDto);

        assertEquals(new BigDecimal("70"), novoLanche.getValorTotal());
        assertEquals(new BigDecimal("27.00"), novoLanche.getDesconto());
    }

    @Test
    public void obtemTodosOsLanchesDoCardapioCorretamente() {
        Iterable<LancheDto> lancheDtos = this.lancheService.obtemLanchesDoCardapio();
        assertNotNull(lancheDtos);
        assertFalse(((Collection<LancheDto>)lancheDtos).isEmpty());
        assertEquals(4, ((Collection<LancheDto>)lancheDtos).size());
    }
}