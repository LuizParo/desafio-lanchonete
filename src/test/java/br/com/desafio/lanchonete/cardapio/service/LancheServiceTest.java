package br.com.desafio.lanchonete.cardapio.service;

import br.com.desafio.lanchonete.cardapio.api.LancheDto;
import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LancheServiceTest {

    @InjectMocks
    private LancheService lancheService;

    @Mock
    private GerenciadorDePreco gerenciadorDePreco;

    private LancheDto lancheDto;

    @Before
    public void setUp() {
        Ingrediente queijo = new Ingrediente("Queijo", BigDecimal.TEN);
        Ingrediente ovo = new Ingrediente("Ovo", BigDecimal.TEN);

        Lanche lanche = new Lanche("X-Burger", Arrays.asList(queijo, ovo));
        this.lancheDto = LancheDto.toDto(lanche);
    }

    @Test
    public void criaLancheChamandoTodasAsDependencias() {
        LancheDto dto = this.lancheService.criaLanche(this.lancheDto);

        verify(this.gerenciadorDePreco, only()).precificaOLanche(any(Lanche.class));
        assertNotNull(dto);
    }
}