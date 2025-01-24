package com.vantutran2k1.notificationservice.services;

import com.vantutran2k1.notificationservice.models.UserRegistrationEvent;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationListener {
    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);
    private final EmailService emailService;

    @KafkaListener(
            topics = "users.user_registrations",
            containerFactory = "userRegistrationListenerContainerFactory"
    )
    public void listen(UserRegistrationEvent event) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("userName", event.getFirstName() + " " + event.getLastName());
        templateModel.put("verificationLink", "http://localhost:5173/verify-user");
        templateModel.put("verificationToken", event.getVerificationToken());

        try {
            emailService.sendEmail(event.getEmail(), "Welcome to MovieHub", templateModel);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
