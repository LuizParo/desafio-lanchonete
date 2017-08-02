package br.com.desafio.lanchonete.cardapio.api;

import br.com.desafio.lanchonete.cardapio.service.LancheService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {
    private final LancheService lancheService;

    @Autowired
    public CardapioController(LancheService lancheService) {
        this.lancheService = lancheService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LancheDto criaLanche(@RequestBody LancheDto lancheDto) {
        return this.lancheService.criaLanche(lancheDto);
    }
}