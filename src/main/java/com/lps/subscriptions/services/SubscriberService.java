package com.lps.subscriptions.services;

import com.lps.subscriptions.model.DTO.SubscriberDTO;
import com.lps.subscriptions.model.Subscriber;
import com.lps.subscriptions.repository.SubscriberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {
    @Autowired
    SubscriberRepository subscriberRepository;
    @Autowired
    private ModelMapper modelMapper;
    public List<Subscriber> getAllSubscribers(){
        return subscriberRepository.findAll();
    }

    public Subscriber saveSubscriber(SubscriberDTO subscriber){
        return subscriberRepository.save(convertToEntity(subscriber));
    }

    public Subscriber convertToEntity(SubscriberDTO subscriberDTO) {
        return modelMapper.map(subscriberDTO, Subscriber.class);
    }

    public SubscriberDTO convertToDTO(Subscriber subscriber) {
        return modelMapper.map(subscriber, SubscriberDTO.class);
    }
}
