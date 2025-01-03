package com.vantutran2k1.notificationservice.services;

import com.vantutran2k1.notificationservice.models.UserRegistrationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
    @KafkaListener(
            topics = "user-registrations",
            containerFactory = "userRegistrationEventConcurrentKafkaListenerContainerFactory"
    )
    public void listen(UserRegistrationEvent event) {
        System.out.println("Full name: " + event.getFirstName() + " " + event.getLastName());
    }
}
