package br.com.desafio.lanchonete.cardapio.servico;

import br.com.desafio.lanchonete.api.dto.IngredienteDto;
import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.preco.PrecoRepository;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
    private final PrecoRepository precoRepository;

    @Autowired
    public IngredienteService(PrecoRepository precoRepository) {
        this.precoRepository = precoRepository;
    }

    public Iterable<IngredienteDto> obtemIngredientesComPreco() {
        Iterable<Ingrediente> ingredientes = this.precoRepository.obtemIngredientesComPreco();

        return StreamSupport.stream(ingredientes.spliterator(), false)
            .map(IngredienteDto::toDto)
            .collect(Collectors.toList());
    }
}