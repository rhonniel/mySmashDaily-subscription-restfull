package com.lps.subscriptions.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class SubscriberControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAllSubscribers() throws Exception {

        mockMvc.perform(get("/subscriber")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(10)));
    }
}
