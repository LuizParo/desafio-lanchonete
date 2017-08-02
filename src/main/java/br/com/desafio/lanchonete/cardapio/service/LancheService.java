package br.com.desafio.lanchonete.cardapio.service;

import br.com.desafio.lanchonete.cardapio.api.LancheDto;
import br.com.desafio.lanchonete.cardapio.model.Lanche;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancheService {
    private final GerenciadorDePreco gerenciadorDePreco;

    @Autowired
    public LancheService(GerenciadorDePreco gerenciadorDePreco) {
        this.gerenciadorDePreco = gerenciadorDePreco;
    }

    public LancheDto criaLanche(LancheDto lancheDto) {
        Lanche lanche = lancheDto.toEntidade();
        this.gerenciadorDePreco.precificaOLanche(lanche);

        return LancheDto.toDto(lanche);
    }
}