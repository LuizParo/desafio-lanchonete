package br.com.desafio.lanchonete.cardapio.repository;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.repository.PrecoRepository;
import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PrecoRepositoryEmMemoriaTest {

    @Autowired
    private PrecoRepository precoRepository;

    @Test
    public void buscaTodosOsIngredientesComSeusRespectivosPrecos() {
        Iterable<Ingrediente> ingredientesComPreco = this.precoRepository.obtemIngredientesComPreco();

        assertNotNull(ingredientesComPreco);
        assertFalse(((Collection<Ingrediente>)ingredientesComPreco).isEmpty());
        ingredientesComPreco.forEach(ingrediente -> assertNotEquals(BigDecimal.ZERO, ingrediente.getValor()));
    }
}