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
}