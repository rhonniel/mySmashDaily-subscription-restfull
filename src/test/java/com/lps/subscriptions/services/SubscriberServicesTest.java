package com.lps.subscriptions.services;

import com.github.javafaker.Faker;
import com.lps.subscriptions.model.DTO.SubscriberDTO;
import com.lps.subscriptions.model.DTO.SubscriptionDTO;
import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.model.Subscription;
import com.lps.subscriptions.model.SubscriptionType;
import com.lps.subscriptions.repository.SubscriberRepository;
import com.lps.subscriptions.security.JwtTokenUtil;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubscriberServicesTest {

    @Mock
    private SubscriberRepository subscriberRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;
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


    @Test
    public void cancelSubscriberSucceed(){
        Subscriber subscriber=buildSubscriber();
        UUID id=UUID.randomUUID();

        doNothing().when(jwtTokenUtil).validateToken(anyString()); // simula una llamada void  para dar clarida
        when(jwtTokenUtil.extractEntityId(anyString())).thenReturn(id);
        when(subscriberRepository.findById(id)).thenReturn(Optional.of(subscriber));
        subscriberService.cancelSubscriber(anyString());

        assertFalse(subscriber.getSubscriptionList().get(0).isActive());
        verify(subscriberRepository).save(subscriber); // el metodo save no requiere ser simulado porque es void  no requiere mokearse ya es claro su concepto
    }


    @Test
    public void cancelSubscriberWithInvalidToken(){

        doThrow(new IllegalArgumentException("Token validation failed")).when(jwtTokenUtil).validateToken(anyString());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            subscriberService.cancelSubscriber(anyString());
        });

        // Verificar el mensaje de la excepción
        assertEquals("Token validation failed", thrown.getMessage());

    }

    @Test
    public void cancelSubscriberWithInvalidSubscriber(){

        UUID id=UUID.randomUUID();
        doNothing().when(jwtTokenUtil).validateToken(anyString()); // simula una llamada void  para dar clarida
        when(jwtTokenUtil.extractEntityId(anyString())).thenReturn(id);
        doThrow(new EntityNotFoundException("Exception message")).when(subscriberRepository).findById(id);


        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            subscriberService.cancelSubscriber(anyString());
        });


        assertEquals("Exception message", thrown.getMessage());

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
