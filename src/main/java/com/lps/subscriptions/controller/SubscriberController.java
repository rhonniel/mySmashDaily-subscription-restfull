package com.lps.subscriptions.controller;

import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.services.SubscriberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"*"}, exposedHeaders = {"Content-Disposition"})
@RequestMapping(value = "/subscriber")
public class SubscriberController {
    @Autowired
    SubscriberService subscriberService;


    @GetMapping
    public List<Subscriber> getAllSubscriber(){

        return subscriberService.getAllSubscribers();
    }
    @PostMapping
    public Subscriber createSubscriber(@Valid @RequestBody Subscriber subscriber){
        return subscriberService.saveSubscriber(subscriber);
    }
}
