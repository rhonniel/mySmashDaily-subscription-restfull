package com.lps.subscriptions.controller;

import com.lps.subscriptions.security.JwtTokenUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SubscriberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String jwtToken;
    @Value("${client.id}")
    private String clientId;
    @BeforeEach
    public void setUp() {
        jwtToken=jwtTokenUtil.generateTokenForClient(clientId);
    }
    @Test
    public void getAllSubscribers() throws Exception {

        mockMvc.perform(get("/subscriber")
                        .header("Authorization","Bearer "+jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(10)));
    }
}
