package br.com.desafio.lanchonete.cardapio.service;

import br.com.desafio.lanchonete.cardapio.api.LancheDto;
import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import br.com.desafio.lanchonete.cardapio.repository.CardapioRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LancheServiceTest {

    @InjectMocks
    private LancheService lancheService;

    @Mock
    private GerenciadorDePreco gerenciadorDePreco;

    @Mock
    private CardapioRepository cardapioRepository;

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

    @Test
    public void obtemTodosOsLanchesDoCardapioCorretamente() {
        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Ovo", new BigDecimal("0.80")),
                new Ingrediente("Bacon", new BigDecimal("2.00")),
                new Ingrediente("Hambúrguer de carne", new BigDecimal("3.00")),
                new Ingrediente("Queijo", new BigDecimal("1.50"))
        );

        Lanche lanche = new Lanche("X-Egg Bacon", ingredientes);

        when(this.cardapioRepository.obtemLanchesProntosDoCardapio()).thenReturn(Arrays.asList(lanche));

        Iterable<LancheDto> lancheDtos = this.lancheService.obtemLanchesDoCardapio();
        assertNotNull(lancheDtos);
        assertFalse(((Collection<LancheDto>)lancheDtos).isEmpty());
        assertEquals(1, ((Collection<LancheDto>)lancheDtos).size());
    }
}