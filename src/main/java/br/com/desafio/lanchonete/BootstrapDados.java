package br.com.desafio.lanchonete;

import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import br.com.desafio.lanchonete.cardapio.repository.CardapioRepository;
import br.com.desafio.lanchonete.cardapio.repository.PrecoRepository;
import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class BootstrapDados implements ApplicationListener<ContextRefreshedEvent> {
    private static final Ingrediente ALFACE = new Ingrediente("Alface", new BigDecimal("0.40"));
    private static final Ingrediente BACON = new Ingrediente("Bacon", new BigDecimal("2.00"));
    private static final Ingrediente CARNE = new Ingrediente("Hambúrguer de carne", new BigDecimal("3.00"));
    private static final Ingrediente OVO = new Ingrediente("Ovo", new BigDecimal("0.80"));
    private static final Ingrediente QUEIJO = new Ingrediente("Queijo", new BigDecimal("1.50"));

    private final PrecoRepository precoRepository;
    private final CardapioRepository cardapioRepository;

    @Autowired
    public BootstrapDados(PrecoRepository precoRepository, CardapioRepository cardapioRepository) {
        this.precoRepository = precoRepository;
        this.cardapioRepository = cardapioRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.precoRepository.salvaPrecoParaOIngrediente(ALFACE.getNome(), ALFACE.getValor());
        this.precoRepository.salvaPrecoParaOIngrediente(BACON.getNome(), BACON.getValor());
        this.precoRepository.salvaPrecoParaOIngrediente(CARNE.getNome(), CARNE.getValor());
        this.precoRepository.salvaPrecoParaOIngrediente(OVO.getNome(), OVO.getValor());
        this.precoRepository.salvaPrecoParaOIngrediente(QUEIJO.getNome(), QUEIJO.getValor());

        this.cardapioRepository.salvaLancheNoCardapio(new Lanche("X-Bacon", Arrays.asList(BACON, CARNE, QUEIJO)));
        this.cardapioRepository.salvaLancheNoCardapio(new Lanche("X-Burger", Arrays.asList(CARNE, QUEIJO)));
        this.cardapioRepository.salvaLancheNoCardapio(new Lanche("X-Egg", Arrays.asList(OVO, CARNE, QUEIJO)));
        this.cardapioRepository.salvaLancheNoCardapio(new Lanche("X-Egg Bacon", Arrays.asList(OVO, BACON, CARNE, QUEIJO)));
    }
}