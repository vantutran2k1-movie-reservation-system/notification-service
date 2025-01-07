package com.vantutran2k1.notificationservice.services;

import com.vantutran2k1.notificationservice.models.UserRegistrationEvent;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationListener {
    private final EmailService emailService;

    @KafkaListener(
            topics = "user-registrations",
            containerFactory = "userRegistrationListenerContainerFactory"
    )
    public void listen(UserRegistrationEvent event) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("userName", event.getFirstName() + " " + event.getLastName());
        templateModel.put("verificationLink", "https://example.com?verificationCode=" + event.getVerificationToken());

        try {
            emailService.sendEmail(event.getEmail(), "Welcome to MovieHub", templateModel);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
