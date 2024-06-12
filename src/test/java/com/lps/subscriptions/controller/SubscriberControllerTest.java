package com.lps.subscriptions.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SubscriberControllerTest {
    @Autowired
    private MockMvc mockMvc;



    @Test
    public void getAllSubscribers() throws Exception {


        // Prueba la solicitud GET
        mockMvc.perform(get("/subscribers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("John Doe")))
                .andExpect(jsonPath("$[0].email", is("john.doe@example.com")));
    }
}
