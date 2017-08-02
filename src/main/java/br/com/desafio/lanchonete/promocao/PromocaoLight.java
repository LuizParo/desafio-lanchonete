package br.com.desafio.lanchonete.promocao;

import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class PromocaoLight implements Promocao {

    @Override
    public BigDecimal calculaDesconto(Lanche lanche) {
        if(isNull(lanche)) {
            return BigDecimal.ZERO;
        }

        long quantidadeDeAlface = lanche.getQuantidadeDeAlface();
        long quantidadeDeBacon = lanche.getQuantidadeDeBacon();

        return quantidadeDeAlface > 0 && quantidadeDeBacon == 0
                ? lanche.getValorTotal().multiply(new BigDecimal("0.10"))
                : BigDecimal.ZERO;
    }
}