package com.lps.subscriptions.services;

import com.github.javafaker.Faker;
import com.lps.subscriptions.model.DTO.SubscriberDTO;
import com.lps.subscriptions.model.DTO.SubscriptionDTO;
import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.model.Subscription;
import com.lps.subscriptions.model.SubscriptionType;
import com.lps.subscriptions.repository.SubscriberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubscriberServicesTest {

    @Mock
    private SubscriberRepository subscriberRepository;
    @Mock
    private ModelMapper modelMapper;
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

    @Test
    public void createSubscriberSucceed(){

        Subscriber subscriber= buildSubscriber();

        SubscriberDTO subscriberDTO= modelMapper.map(subscriber, SubscriberDTO.class);

        when(subscriberRepository.save(subscriber)).thenReturn(subscriber);
        when(modelMapper.map(subscriberDTO, Subscriber.class)).thenReturn(subscriber);

       Subscriber subscriberResult= subscriberService.saveSubscriber(subscriberDTO);

        assertEquals(subscriberResult,subscriber);

    }
    


    private Subscriber buildSubscriber(){
        Subscriber subscriber = new Subscriber();
        subscriber.setName(faker.name().fullName());
        subscriber.setEmail(faker.internet().emailAddress());

        List<Subscription> subscriptionList = new ArrayList<>();
        Subscription subscription = new Subscription();
        subscription.setSubscriptionType(SubscriptionType.PLAYER);
        subscription.setConfiguration("{'vaina':'Vaina'}");

        subscriptionList.add(subscription);

        subscriber.setSubscriptionList(subscriptionList);

        return subscriber;
    }

    private List<Subscriber> generateSubscribers(int quantity) {
        List<Subscriber> lista = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Subscriber s = new Subscriber();
            s.setName(faker.name().fullName());
            s.setEmail(faker.internet().emailAddress());
            lista.add(s);
        }
        return lista;
    }
}
