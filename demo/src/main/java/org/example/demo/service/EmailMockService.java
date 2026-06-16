package org.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class EmailMockService {
    public void sendConfirmationEmail(String email, String messageSubject) {
        System.out.println("--- EMAIL MOCK ---");
        System.out.println("To: " + email);
        System.out.println("Subject: " + messageSubject);
        System.out.println("Body: Your appointment has been confirmed!");
        System.out.println("------------------");
    }
}
