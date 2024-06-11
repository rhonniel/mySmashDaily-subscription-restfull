package com.lps.subscriptions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    private int id;
    private Subscriber subscriber;
    private SubscriptionType subscriptionType;
    private String filtersConfigurations;
    private Date creationDate;
    private boolean active;

}
