package br.com.desafio.lanchonete.cardapio.model;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

public class LancheTest {

    @Test
    public void criaLancheComNomeVazioSeNullForPasadoComoParametroParaEste() {
        Lanche lanche = new Lanche(null, new ArrayList<>());
        assertNotNull(lanche.getNome());
        assertEquals("", lanche.getNome());
    }

    @Test
    public void CriaDescontoComoZeroSeValorPassadoForNull() {
        Lanche lanche = new Lanche(null, new ArrayList<>());
        lanche.setDesconto(null);

        assertNotNull(lanche.getDesconto());
        assertEquals(BigDecimal.ZERO, lanche.getDesconto());
    }

    @Test
    public void criaLancheComSemIngredientesSeNullForPasadoComoParametroParaEste() {
        Lanche lanche = new Lanche("X-Bacon", null);
        assertNotNull(lanche.getIngredientes());
        assertTrue(lanche.getIngredientes().isEmpty());
    }

    @Test
    public void valorDoDescontoEhInicializadoComZeroAutomaticamente() {
        Lanche lanche = new Lanche("X-Bacon", null);
        assertNotNull(lanche.getDesconto());
        assertEquals(BigDecimal.ZERO, lanche.getDesconto());
    }

    @Test
    public void calculaValorTotalDoLancheComoZeroSeNenhumIngredienteEstiverPreente() {
        Lanche lanche = new Lanche("X-Bacon", new HashSet<>());
        assertEquals(BigDecimal.ZERO, lanche.getValorTotal());
    }

    @Test
    public void calculaValorTotalDoLancheSomandoOsIngredientesDoXBacon() {
        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Bacon", new BigDecimal("2.00")),
                new Ingrediente("Hambúrguer de carne", new BigDecimal("3.00")),
                new Ingrediente("Queijo", new BigDecimal("1.50"))
        );

        Lanche lanche = new Lanche("X-Bacon", ingredientes);
        assertEquals(new BigDecimal("6.50"), lanche.getValorTotal());
    }

    @Test
    public void calculaValorTotalDoLancheSomandoOsIngredientesDoXBurger() {
        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Hambúrguer de carne", new BigDecimal("3.00")),
                new Ingrediente("Queijo", new BigDecimal("1.50"))
        );

        Lanche lanche = new Lanche("X-Burger", ingredientes);
        assertEquals(new BigDecimal("4.50"), lanche.getValorTotal());
    }

    @Test
    public void calculaValorTotalDoLancheSomandoOsIngredientesDoXEgg() {
        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Ovo", new BigDecimal("0.80")),
                new Ingrediente("Hambúrguer de carne", new BigDecimal("3.00")),
                new Ingrediente("Queijo", new BigDecimal("1.50"))
        );

        Lanche lanche = new Lanche("X-Egg", ingredientes);
        assertEquals(new BigDecimal("5.30"), lanche.getValorTotal());
    }

    @Test
    public void calculaValorTotalDoLancheSomandoOsIngredientesDoXEggBacon() {
        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Ovo", new BigDecimal("0.80")),
                new Ingrediente("Bacon", new BigDecimal("2.00")),
                new Ingrediente("Hambúrguer de carne", new BigDecimal("3.00")),
                new Ingrediente("Queijo", new BigDecimal("1.50"))
        );

        Lanche lanche = new Lanche("X-Egg Bacon", ingredientes);
        assertEquals(new BigDecimal("7.30"), lanche.getValorTotal());
    }

    @Test
    public void obtemValorDaQuantidadeDeAlfaceComoZeroSeLancheNaoPossuirAlface() {
        Lanche lanche = new Lanche("X-Egg", Arrays.asList(new Ingrediente("Ovo", new BigDecimal("0.80"))));
        assertEquals(0, lanche.getQuantidadeDeAlface());
    }

    @Test
    public void obtemValorDaQuantidadeDeAlfaceComo1SeLanchePossuir1PorcaoDeAlface() {
        Lanche lanche = new Lanche("X-Alface", Arrays.asList(new Ingrediente("Alface", new BigDecimal("0.40"))));
        assertEquals(1, lanche.getQuantidadeDeAlface());
    }

    @Test
    public void obtemValorDaQuantidadeDeBaconComoZeroSeLancheNaoPossuirBacon() {
        Lanche lanche = new Lanche("X-Egg", Arrays.asList(new Ingrediente("Ovo", new BigDecimal("0.80"))));
        assertEquals(0, lanche.getQuantidadeDeBacon());
    }

    @Test
    public void obtemValorDaQuantidadeDeBaconComo1SeLanchePossuir1PorcaoDeBacon() {
        Lanche lanche = new Lanche("X-Bacon", Arrays.asList(new Ingrediente("Bacon", new BigDecimal("0.40"))));
        assertEquals(1, lanche.getQuantidadeDeBacon());
    }

    @Test
    public void obtemValorDaQuantidadeDeCarneComoZeroSeLancheNaoPossuirCarne() {
        Lanche lanche = new Lanche("X-Egg", Arrays.asList(new Ingrediente("Ovo", new BigDecimal("0.80"))));
        assertEquals(0, lanche.getQuantidadeDeCarne());
    }

    @Test
    public void obtemValorDaQuantidadeDeCarneComo1SeLanchePossuir1PorcaoDeCarne() {
        Lanche lanche = new Lanche("X-Bacon", Arrays.asList(new Ingrediente("Hambúrguer de carne", new BigDecimal("0.40"))));
        assertEquals(1, lanche.getQuantidadeDeCarne());
    }

    @Test
    public void obtemValorDaQuantidadeDeOvoComoZeroSeLancheNaoPossuirOvo() {
        Lanche lanche = new Lanche("X-Alace", Arrays.asList(new Ingrediente("Alface", new BigDecimal("0.80"))));
        assertEquals(0, lanche.getQuantidadeDeOvo());
    }

    @Test
    public void obtemValorDaQuantidadeDeOvoComo1SeLanchePossuir1PorcaoDeOvo() {
        Lanche lanche = new Lanche("X-Egg", Arrays.asList(new Ingrediente("Ovo", new BigDecimal("0.40"))));
        assertEquals(1, lanche.getQuantidadeDeOvo());
    }

    @Test
    public void obtemValorDaQuantidadeDeQueijoComoZeroSeLancheNaoPossuirQueijo() {
        Lanche lanche = new Lanche("X-Egg", Arrays.asList(new Ingrediente("Ovo", new BigDecimal("0.80"))));
        assertEquals(0, lanche.getQuantidadeDeQueijo());
    }

    @Test
    public void obtemValorDaQuantidadeDeQueijoComo1SeLanchePossuir1PorcaoDeQueijo() {
        Lanche lanche = new Lanche("X-Burger", Arrays.asList(new Ingrediente("Queijo", new BigDecimal("0.40"))));
        assertEquals(1, lanche.getQuantidadeDeQueijo());
    }

    @Test
    public void obtemValorIndividualDaCarneComoZeroQuandoOLancheNaoPossuirNenhumaPorcaoDeste() {
        Lanche lanche = new Lanche("X-Bacon", Arrays.asList(new Ingrediente("Queijo", BigDecimal.TEN)));

        assertEquals(BigDecimal.ZERO, lanche.getValorIndividualDaCarne());
    }

    @Test
    public void obtemValorIndividualDaCarneCorretamenteQuandoOLanchePossuiSomenteUmaPorcaoDeste() {
        BigDecimal valorDaCarne = new BigDecimal("0.40");
        Lanche lanche = new Lanche("X-Bacon", Arrays.asList(new Ingrediente("Hambúrguer de carne", valorDaCarne)));

        assertEquals(valorDaCarne, lanche.getValorIndividualDaCarne());
    }

    @Test
    public void obtemValorIndividualDaCarneCorretamenteQuandoOLanchePossuiMaisDeUmaPorcaoDeste() {
        BigDecimal valorDaCarne = new BigDecimal("0.40");

        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Hambúrguer de carne", valorDaCarne),
                new Ingrediente("Hambúrguer de carne", valorDaCarne),
                new Ingrediente("Hambúrguer de carne", valorDaCarne)
        );
        Lanche lanche = new Lanche("X-Bacon", ingredientes);

        assertEquals(valorDaCarne, lanche.getValorIndividualDaCarne());
    }

    @Test
    public void obtemValorIndividualDoQueijoComoZeroQuandoOLancheNaoPossuirNenhumaPorcaoDeste() {
        Lanche lanche = new Lanche("X-Egg", Arrays.asList(new Ingrediente("Ovo", BigDecimal.TEN)));

        assertEquals(BigDecimal.ZERO, lanche.getValorIndividualDoQueijo());
    }

    @Test
    public void obtemValorIndividualDoQueijoCorretamenteQuandoOLanchePossuiSomenteUmaPorcaoDeste() {
        BigDecimal valorDoQueijo = new BigDecimal("0.90");
        Lanche lanche = new Lanche("X-Bacon", Arrays.asList(new Ingrediente("Queijo", valorDoQueijo)));

        assertEquals(valorDoQueijo, lanche.getValorIndividualDoQueijo());
    }

    @Test
    public void obtemValorIndividualDoQueijoCorretamenteQuandoOLanchePossuiMaisDeUmaPorcaoDeste() {
        BigDecimal valorDoQueijo = new BigDecimal("0.90");

        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Queijo", valorDoQueijo),
                new Ingrediente("Queijo", valorDoQueijo)
        );
        Lanche lanche = new Lanche("X-Bacon", ingredientes);

        assertEquals(valorDoQueijo, lanche.getValorIndividualDoQueijo());
    }

    @Test
    public void aplicaDescontoNoValorTotalDoLanche() {
        List<Ingrediente> ingredientes = Arrays.asList(
                new Ingrediente("Queijo", BigDecimal.TEN),
                new Ingrediente("Queijo", BigDecimal.TEN)
        );
        Lanche lanche = new Lanche("X-Bacon", ingredientes);
        lanche.setDesconto(BigDecimal.ONE);

        assertEquals(new BigDecimal("19"), lanche.getValorComDesconto());
    }
}