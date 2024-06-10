package com.lps.subscriptions.controller;

import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
