package br.com.desafio.lanchonete.cardapio.service;

import br.com.desafio.lanchonete.cardapio.api.LancheDto;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import br.com.desafio.lanchonete.cardapio.repository.CardapioRepository;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancheService {
    private final GerenciadorDePreco gerenciadorDePreco;
    private final CardapioRepository cardapioRepository;

    @Autowired
    public LancheService(GerenciadorDePreco gerenciadorDePreco, CardapioRepository cardapioRepository) {
        this.gerenciadorDePreco = gerenciadorDePreco;
        this.cardapioRepository = cardapioRepository;
    }

    public LancheDto criaLanche(LancheDto lancheDto) {
        Lanche lanche = lancheDto.toEntidade();
        this.gerenciadorDePreco.precificaOLanche(lanche);

        return LancheDto.toDto(lanche);
    }

    public Iterable<LancheDto> obtemLanchesDoCardapio() {
        Iterable<Lanche> lanches = this.cardapioRepository.obtemLanchesProntosDoCardapio();

        return StreamSupport.stream(lanches.spliterator(), false)
            .map(LancheDto::toDto)
            .collect(Collectors.toList());
    }
}