package br.com.desafio.lanchonete.cardapio.api;

import br.com.desafio.lanchonete.LanchoneteApplication;
import br.com.desafio.lanchonete.cardapio.model.Ingrediente;
import br.com.desafio.lanchonete.cardapio.model.Lanche;
import java.math.BigDecimal;
import java.util.Arrays;
import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LanchoneteApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class CardapioControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void criaLancheConsiderandoDescontos() throws Exception {
        Ingrediente alface = new Ingrediente("Alface", BigDecimal.TEN);
        Ingrediente queijo1 = new Ingrediente("Queijo", BigDecimal.TEN);
        Ingrediente queijo2 = new Ingrediente("Queijo", BigDecimal.TEN);
        Ingrediente queijo3 = new Ingrediente("Queijo", BigDecimal.TEN);
        Ingrediente carne1 = new Ingrediente("Hambúrguer de carne", BigDecimal.TEN);
        Ingrediente carne2 = new Ingrediente("Hambúrguer de carne", BigDecimal.TEN);
        Ingrediente carne3 = new Ingrediente("Hambúrguer de carne", BigDecimal.TEN);

        Lanche lanche = new Lanche("X-Burger", Arrays.asList(alface, queijo1, queijo2, queijo3, carne1, carne2, carne3));
        LancheDto lancheDto = LancheDto.toDto(lanche);

        String jsonLanche = new ObjectMapper().writeValueAsString(lancheDto);

        MvcResult result = this.mockMvc
                .perform(
                        post("/cardapios")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(jsonLanche)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nome").value("X-Burger"))
                .andExpect(jsonPath("$.valorTotal").value(new BigDecimal("70")))
                .andExpect(jsonPath("$.desconto").value(new BigDecimal("27.0")))
                .andExpect(jsonPath("$.ingredientes[0].nome").value(lanche.getIngredientes().iterator().next().getNome()))
                .andExpect(jsonPath("$.ingredientes[0].valor").value(lanche.getIngredientes().iterator().next().getValor()))
                .andReturn();

        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, result.getResponse().getContentType());
    }

    @Test
    public void buscaTodosOsLanchesDoCardapio() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/cardapios"))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].nome").value("X-Bacon"))
                .andExpect(jsonPath("$[0].valorTotal").value(new BigDecimal("6.5")))
                .andExpect(jsonPath("$[0].desconto").value(new BigDecimal("0")))
                .andExpect(jsonPath("$[0].ingredientes[0].nome").value("Bacon"))
                .andExpect(jsonPath("$[0].ingredientes[0].valor").value(new BigDecimal("2.0")))
                .andExpect(jsonPath("$[0].ingredientes[1].nome").value("Hambúrguer de carne"))
                .andExpect(jsonPath("$[0].ingredientes[1].valor").value(new BigDecimal("3.0")))
                .andExpect(jsonPath("$[0].ingredientes[2].nome").value("Queijo"))
                .andExpect(jsonPath("$[0].ingredientes[2].valor").value(new BigDecimal("1.5")))

                .andExpect(jsonPath("$[1].nome").value("X-Egg"))
                .andExpect(jsonPath("$[1].valorTotal").value(new BigDecimal("5.3")))
                .andExpect(jsonPath("$[1].desconto").value(new BigDecimal("0")))
                .andExpect(jsonPath("$[1].ingredientes[0].nome").value("Ovo"))
                .andExpect(jsonPath("$[1].ingredientes[0].valor").value(new BigDecimal("0.8")))
                .andExpect(jsonPath("$[1].ingredientes[1].nome").value("Hambúrguer de carne"))
                .andExpect(jsonPath("$[1].ingredientes[1].valor").value(new BigDecimal("3.0")))
                .andExpect(jsonPath("$[1].ingredientes[2].nome").value("Queijo"))
                .andExpect(jsonPath("$[1].ingredientes[2].valor").value(new BigDecimal("1.5")))

                .andExpect(jsonPath("$[2].nome").value("X-Egg Bacon"))
                .andExpect(jsonPath("$[2].valorTotal").value(new BigDecimal("7.3")))
                .andExpect(jsonPath("$[2].desconto").value(new BigDecimal("0")))
                .andExpect(jsonPath("$[2].ingredientes[0].nome").value("Ovo"))
                .andExpect(jsonPath("$[2].ingredientes[0].valor").value(new BigDecimal("0.8")))
                .andExpect(jsonPath("$[2].ingredientes[1].nome").value("Bacon"))
                .andExpect(jsonPath("$[2].ingredientes[1].valor").value(new BigDecimal("2.0")))
                .andExpect(jsonPath("$[2].ingredientes[2].nome").value("Hambúrguer de carne"))
                .andExpect(jsonPath("$[2].ingredientes[2].valor").value(new BigDecimal("3.0")))
                .andExpect(jsonPath("$[2].ingredientes[3].nome").value("Queijo"))
                .andExpect(jsonPath("$[2].ingredientes[3].valor").value(new BigDecimal("1.5")))

                .andExpect(jsonPath("$[3].nome").value("X-Burger"))
                .andExpect(jsonPath("$[3].valorTotal").value(new BigDecimal("4.5")))
                .andExpect(jsonPath("$[3].desconto").value(new BigDecimal("0")))
                .andExpect(jsonPath("$[3].ingredientes[0].nome").value("Hambúrguer de carne"))
                .andExpect(jsonPath("$[3].ingredientes[0].valor").value(new BigDecimal("3.0")))
                .andExpect(jsonPath("$[3].ingredientes[1].nome").value("Queijo"))
                .andExpect(jsonPath("$[3].ingredientes[1].valor").value(new BigDecimal("1.5")))

                .andReturn();

        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, result.getResponse().getContentType());
    }
}