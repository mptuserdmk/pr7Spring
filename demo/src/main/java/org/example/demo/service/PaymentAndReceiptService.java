package org.example.demo.service;

import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PaymentAndReceiptService {
    
    public boolean processPayment(Long appointmentId, String amount) {
        System.out.println("Processing payment for appointment " + appointmentId + ": " + amount + " RUB");
        return true; 
    }
    
    public void generateReceipt(Long appointmentId, String serviceName, String price) {
        String fileName = "receipt_" + appointmentId + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("======= BARBERSHOP CHEQUE =======\n");
            writer.write("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("Appointment ID: " + appointmentId + "\n");
            writer.write("Service: " + serviceName + "\n");
            writer.write("Price: " + price + " RUB\n");
            writer.write("Status: PAID\n");
            writer.write("=================================\n");
            System.out.println("Receipt generated: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to write receipt: " + e.getMessage());
        }
    }
}
