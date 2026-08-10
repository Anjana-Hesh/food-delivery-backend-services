package com.bytebites.paymentservice.controller;

import com.bytebites.paymentservice.model.PaymentLog;
import com.bytebites.paymentservice.repository.PaymentLogRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentLogRepository paymentLogRepository;

    @PostConstruct
    public void init() {
        if (paymentLogRepository.count() == 0) {
            paymentLogRepository.save(new PaymentLog("1", "Amara Perera", 5350.00, "SUCCESS", "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()));
            paymentLogRepository.save(new PaymentLog("2", "Nimal Fernando", 5150.00, "SUCCESS", "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()));
            paymentLogRepository.save(new PaymentLog("3", "Saman Kumara", 4470.00, "SUCCESS", "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()));
            System.out.println("Prepopulated 3 default payment logs in PostgreSQL");
        }
    }

    @GetMapping("/logs")
    public List<PaymentLog> getAllLogs() {
        return paymentLogRepository.findAll();
    }

    @PostMapping("/charge")
    public ResponseEntity<Map<String, Object>> processPayment(@RequestBody Map<String, Object> request) {
        String orderId = (String) request.get("orderId");
        double amount = Double.parseDouble(request.get("amount").toString());
        String customerName = (String) request.get("customerName");

        String transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String status = "SUCCESS";

        // Mock fail condition for large transactions
        if (amount > 100000.0) {
            status = "FAILED";
        }

        PaymentLog log = new PaymentLog(orderId, customerName, amount, status, transactionId);
        paymentLogRepository.save(log);

        Map<String, Object> response = new HashMap<>();
        response.put("success", status.equals("SUCCESS"));
        response.put("transactionId", transactionId);
        response.put("message", "Payment processed status: " + status);

        return ResponseEntity.ok(response);
    }
}
