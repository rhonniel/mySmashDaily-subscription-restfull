package com.lps.subscriptions.services;

import com.github.javafaker.Faker;
import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.repository.SubscriberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubscriberServicesTest {

    @Mock
    private SubscriberRepository subscriberRepository;
    @InjectMocks
    private SubscriberService subscriberService;

    private Faker faker;
    @BeforeEach
    public void setUp() {
        faker=new Faker();
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getAllSubscriberSucceed(){
        List<Subscriber> subscribers=generateSubscribers(10);
        when(subscriberRepository.findAll()).thenReturn(subscribers);
        assertEquals(subscribers,subscriberService.getAllSubscribers());
    }
    private List<Subscriber> generateSubscribers(int quantity) {
        List<Subscriber> lista = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Subscriber subscriber = new Subscriber();
            subscriber.setName(faker.name().fullName());
            subscriber.setEmail(faker.internet().emailAddress());
            lista.add(subscriber);
        }
        return lista;
    }

}
