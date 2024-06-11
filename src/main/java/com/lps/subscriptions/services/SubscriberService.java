package com.lps.subscriptions.services;

import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {
    @Autowired
    SubscriberRepository subscriberRepository;

    public List<Subscriber> getAllSubscribers(){
       return subscriberRepository.findAll();
    }
}
