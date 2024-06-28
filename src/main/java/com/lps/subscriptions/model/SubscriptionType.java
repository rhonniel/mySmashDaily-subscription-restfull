package com.lps.subscriptions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubscriptionType {

    public static SubscriptionType TOURNEY= new SubscriptionType(1,"Tournament Tracker");
    public static SubscriptionType PLAYER= new SubscriptionType(2,"Favorite Player");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
}
