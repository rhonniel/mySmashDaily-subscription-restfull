package com.lps.subscriptions.repository;

import com.lps.subscriptions.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository es una plantilla de repositorio mas enfocada a entidades jpa
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, UUID> {
}
