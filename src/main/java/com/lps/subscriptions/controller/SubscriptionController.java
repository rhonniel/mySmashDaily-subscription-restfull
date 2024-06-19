package com.lps.subscriptions.controller;

import com.lps.subscriptions.services.SubscriptionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionServices subscriptionServices;

    @GetMapping
    public String getAllSubscriber(){
        return subscriptionServices.getHello();
    }

}
