package br.com.desafio.lanchonete.cardapio.model;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class PreparadorDeLanche {

    public Lanche preparaOLanche(String nomeDoLanche, Collection<Ingrediente> ingredientes) {
        return new Lanche(nomeDoLanche, ingredientes);
    }
}