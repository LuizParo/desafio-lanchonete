package br.com.desafio.lanchonete.cardapio.repository;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CardapioRepositoryEmMemoriaTest {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Test
    public void buscaTodosOsLanchesProntosDoCardapio() {
        Iterable<Lanche> lanches = this.cardapioRepository.obtemLanchesProntosDoCardapio();

        assertNotNull(lanches);
        assertFalse(((Collection<Lanche>)lanches).isEmpty());
        assertEquals(4, ((Collection<Lanche>)lanches).size());
    }
}