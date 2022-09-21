package com.leovegas.walletmanager;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leovegas.walletmanager.entities.Player;
import com.leovegas.walletmanager.entities.Transaction;
import com.leovegas.walletmanager.service.SeedingService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class WalletManagerApplicationTests {

    @Autowired
    private SeedingService seedingService;

    @Autowired
    private MockMvc mockMvc;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void gePlayerBalanceTest() throws Exception {
        // given - setup
        Player player = seedingService.createTransaction();

        // when - action

        // String inputJson = mapToJson(player);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/wallet/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        // then - verify
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(content);
//
//        BigDecimal balance = (BigDecimal) json.get("balance");
        assertEquals(content, "2000.00");


    }


    @Test
    void makeCreditTest() throws Exception {

        // given - setup
        seedingService.createPlayer();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/wallet/credit/1/500")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        // then - verify
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(content);

        Double balance = (Double) json.get("balance");
        assertEquals(500.00, balance);

    }

    @Test
    void makeDebitTest() throws Exception {
        // given - setup
        seedingService.createPlayer();

        // when - action
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/wallet/debit/2/500")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        // then - verify
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(content);

        Double balance = (Double) json.get("balance");
        assertEquals(1500.00, balance);
    }

    @Test
    void getTransactionPlayerTest() throws Exception {
        // given - setup
        Player player = seedingService.createTransaction();

        // when - action
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/wallet/transactions/" + player.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        // then - verify
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Transaction[] transactions = mapFromJson(content, Transaction[].class);
        assertTrue(transactions.length > 0);
    }


}
