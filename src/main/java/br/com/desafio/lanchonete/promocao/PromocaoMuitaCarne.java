package br.com.desafio.lanchonete.promocao;

import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.stream.LongStream;

import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class PromocaoMuitaCarne implements Promocao {

    @Override
    public BigDecimal calculaDesconto(Lanche lanche) {
        if(isNull(lanche)) {
            return BigDecimal.ZERO;
        }

        long quantidadeDeCarneASerDescontada = LongStream.range(1, lanche.getQuantidadeDeCarne() + 1)
                .filter(numero -> numero % 3 == 0)
                .count();

        return lanche.getValorIndividualDaCarne()
            .multiply(BigDecimal.valueOf(quantidadeDeCarneASerDescontada));
    }
}