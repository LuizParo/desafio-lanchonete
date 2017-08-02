package br.com.desafio.lanchonete.preco;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
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
    public void buscaPrecoCorretoQuandoIngredienteExistir() {
        BigDecimal valor = this.precoRepository.obtemPrecoDoIngrediente("Alface");
        assertEquals(new BigDecimal("0.40"), valor);
    }

    @Test
    public void buscaPrecoComoZeroQuandoIngredienteNaoExistir() {
        BigDecimal valor = this.precoRepository.obtemPrecoDoIngrediente("Salsicha");
        assertEquals(BigDecimal.ZERO, valor);
    }

    @Test
    public void buscaPrecoComoZeroQuandoIngredienteForNull() {
        BigDecimal valor = this.precoRepository.obtemPrecoDoIngrediente(null);
        assertEquals(BigDecimal.ZERO, valor);
    }

    @Test
    public void buscaTodosOsIngredientesComSeusRespectivosPrecos() {
        Iterable<Ingrediente> ingredientesComPreco = this.precoRepository.obtemIngredientesComPreco();

        assertNotNull(ingredientesComPreco);
        assertFalse(((Collection<Ingrediente>)ingredientesComPreco).isEmpty());
        ingredientesComPreco.forEach(ingrediente -> assertNotEquals(BigDecimal.ZERO, ingrediente.getValor()));
    }
}