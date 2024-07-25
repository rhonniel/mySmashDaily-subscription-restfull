package com.lps.subscriptions.controller;

import com.lps.subscriptions.model.DTO.SubscriberDTO;
import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.services.SubscriberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subscriber")
public class SubscriberController {
    @Autowired
    SubscriberService subscriberService;


    @GetMapping
    public List<Subscriber> getAllSubscriber(){
        return subscriberService.getAllSubscribers();
    }
    @PostMapping
    public Subscriber createSubscriber(@Valid @RequestBody SubscriberDTO subscriber){
        return subscriberService.saveSubscriber(subscriber);
    }

    @PutMapping
    public Subscriber cancelSubscriber(@RequestHeader("Authorization") String token){
        return subscriberService.cancelSubscriber(token);
    }
}
