package com.lps.subscriptions.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.model.Subscription;
import com.lps.subscriptions.model.SubscriptionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void createSubscriberWhenSucceed() throws Exception {
        Subscriber newSubscriber =new Subscriber();
        newSubscriber.setName(faker.name().fullName());
        newSubscriber.setEmail(faker.internet().emailAddress());
        newSubscriber.setCreationDate(new Date());

        List<Subscription> subscriptionList =new ArrayList<>();

        Subscription torneySubscription =new Subscription();
        torneySubscription.setSubscriber(newSubscriber);
        torneySubscription.setSubscriptionType(SubscriptionType.TOURNEY);
        torneySubscription.setCreationDate(new Date());
        torneySubscription.setConfiguration(null);


        Subscription playerSubscription =new Subscription();
        playerSubscription.setSubscriber(newSubscriber);
        playerSubscription.setSubscriptionType(SubscriptionType.PLAYER);
        playerSubscription.setCreationDate(new Date());
        playerSubscription.setConfiguration(null);

        subscriptionList.add(torneySubscription);
        subscriptionList.add(playerSubscription);

        newSubscriber.setSubscriptionList(subscriptionList);
        mockMvc.perform(post("/subscriber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSubscriber))
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(newSubscriber.getName()));



    }
}
