package com.example.SRK.Backend.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.to}")
    private String recipient;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${app.mail.cc:}")
    private String ccList;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendFormSubmissionEmail(String formType, String name, String email, String phone, Map<String, String> additionalData) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);

        if (ccList != null && !ccList.isBlank()) {
            String[] ccArray = ccList.split(",");
            message.setCc(ccArray);
        }

        // Add spaces in subject for readability
        message.setSubject("New " + (formType != null ? formType : "Unknown") + " Submission");

        StringBuilder body = new StringBuilder();
        body.append("Form Type: ").append(formType != null ? formType : "N/A").append("\n");

        if (name != null && !name.isBlank()) {
            body.append("Name: ").append(name).append("\n");
        }
        if (email != null && !email.isBlank()) {
            body.append("Email: ").append(email).append("\n");
        }
        if (phone != null && !phone.isBlank()) {
            body.append("Phone: ").append(phone).append("\n");
        }

        // Add additional data if present
        if (additionalData != null && !additionalData.isEmpty()) {
            additionalData.forEach((key, value) -> {
                body.append(key).append(": ").append(value != null ? value : "N/A").append("\n");
            });
        }

        message.setText(body.toString());
        mailSender.send(message);
    }
}
