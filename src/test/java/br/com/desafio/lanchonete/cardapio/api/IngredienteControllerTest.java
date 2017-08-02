package br.com.desafio.lanchonete.cardapio.api;

import br.com.desafio.lanchonete.LanchoneteApplication;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LanchoneteApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class IngredienteControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void buscaTodosOsIngredientesComValor() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/ingredientes"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nome").value("Alface"))
            .andExpect(jsonPath("$[0].valor").value(new BigDecimal("0.4")))
            .andReturn();

        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, result.getResponse().getContentType());
    }
}