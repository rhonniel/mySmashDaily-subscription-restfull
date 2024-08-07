package com.lps.subscriptions.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    @JsonBackReference
    private Subscriber subscriber;

    @ManyToOne
    @JoinColumn(name = "subscription_type_id")
    private SubscriptionType subscriptionType;

    @Column(name = "configuration")
    private String configuration;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column
    private boolean active;

}
