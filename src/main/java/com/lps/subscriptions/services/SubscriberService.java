package com.lps.subscriptions.services;

import com.lps.subscriptions.model.DTO.SubscriberDTO;
import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.repository.SubscriberRepository;
import com.lps.subscriptions.security.JwtTokenUtil;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriberService {
    @Autowired
    SubscriberRepository subscriberRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;
    public List<Subscriber> getAllSubscribers(){
        return subscriberRepository.findAll();
    }

    public Subscriber saveSubscriber(SubscriberDTO subscriber){
        return subscriberRepository.save(convertToEntity(subscriber));
    }

    public void cancelSubscriber(String token){

        if(jwtTokenUtil.validateToken(token)) {
            Subscriber subscriber= subscriberRepository.findById(jwtTokenUtil.extractEntityId(token))
                    .orElseThrow(() -> new EntityNotFoundException("subscriber not found with id: " + jwtTokenUtil.extractEntityId(token)));
            subscriber.getSubscriptionList().forEach(subscription -> subscription.setActive(false));
            subscriberRepository.save(subscriber);
        }

    }

    public Subscriber convertToEntity(SubscriberDTO subscriberDTO) {
        return modelMapper.map(subscriberDTO, Subscriber.class);
    }

    public SubscriberDTO convertToDTO(Subscriber subscriber) {
        return modelMapper.map(subscriber, SubscriberDTO.class);
    }
}
