package com.lps.subscriptions.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.lps.subscriptions.model.DTO.SubscriberDTO;
import com.lps.subscriptions.model.DTO.SubscriptionDTO;
import com.lps.subscriptions.model.SubscriptionType;
import com.lps.subscriptions.security.JwtTokenUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class SubscriberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Faker faker;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @BeforeEach
    public void setUp() {
        faker=new Faker();
    }
    @Test
    public void getAllSubscribers() throws Exception {

        mockMvc.perform(get("/subscriber")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(10)));
    }

    @Test
    @Transactional
    public void createSubscriberWhenSucceed() throws Exception {
        SubscriberDTO newSubscriberDTO= generateSubscriberDTO();
        mockMvc.perform(post("/subscriber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSubscriberDTO))
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(newSubscriberDTO.getName()));

    }


    @Test
    @Transactional
    public void cancelSubscriberWhenSucceed() throws Exception {
        String token=jwtTokenUtil.generateToken(UUID.fromString("123e4567-e89b-12d3-a456-426614174001"));
        mockMvc.perform(put("/subscriber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization",token)
                        .with(csrf()))
                .andExpect(status().isOk());
    }



    private SubscriberDTO generateSubscriberDTO(){
        SubscriberDTO subscriberDTO =new SubscriberDTO();
        subscriberDTO.setName(faker.name().fullName());
        subscriberDTO.setEmail(faker.internet().emailAddress());


        List<SubscriptionDTO> subscriptionList =new ArrayList<>();

        SubscriptionDTO tourneySubscription =new SubscriptionDTO();

        tourneySubscription.setType(SubscriptionType.TOURNEY.getId());
        tourneySubscription.setConfiguration("{'vaina':'vaina'}");


        SubscriptionDTO playerSubscription =new SubscriptionDTO();
        playerSubscription.setType(SubscriptionType.PLAYER.getId());
        playerSubscription.setConfiguration(null);

        subscriptionList.add(tourneySubscription);
        subscriptionList.add(playerSubscription);

        subscriberDTO.setSubscriptionDTOS(subscriptionList);
        return subscriberDTO;
    }




}
