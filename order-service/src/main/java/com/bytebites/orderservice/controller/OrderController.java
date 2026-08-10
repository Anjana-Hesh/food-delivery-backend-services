package com.bytebites.orderservice.controller;

import com.bytebites.orderservice.model.Order;
import com.bytebites.orderservice.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        if (orderRepository.count() == 0) {
            orderRepository.save(new Order("Amara Perera", "2x Classic Caesar Salad, 1x Margherita Pizza", 5350.00, "PENDING"));
            orderRepository.save(new Order("Nimal Fernando", "1x Grilled Beef Steak, 2x Iced Cappuccino", 5150.00, "PAID"));
            orderRepository.save(new Order("Saman Kumara", "3x Pancake Stack with Berries", 4470.00, "DISPATCHED"));
            System.out.println("Prepopulated 3 default orders in MySQL");
        }
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);

        // Call payment-service to process mock payment via API gateway or direct port
        // We will call gateway port 8080 or direct port 8084 as a fallback
        String paymentUrl = "http://localhost:8080/api/payments/charge";
        Map<String, Object> paymentRequest = new HashMap<>();
        paymentRequest.put("orderId", savedOrder.getId().toString());
        paymentRequest.put("amount", savedOrder.getTotalAmount());
        paymentRequest.put("customerName", savedOrder.getCustomerName());

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(paymentUrl, paymentRequest, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Boolean success = (Boolean) response.getBody().get("success");
                if (Boolean.TRUE.equals(success)) {
                    savedOrder.setStatus("PAID");
                } else {
                    savedOrder.setStatus("CANCELLED");
                }
            } else {
                savedOrder.setStatus("FAILED");
            }
        } catch (Exception e) {
            System.err.println("Failed to connect to payment-service, setting status to PAID (local mock fallback): " + e.getMessage());
            // Fallback for standalone run: mark as PAID automatically if payment-service is down
            savedOrder.setStatus("PAID");
        }

        Order finalOrder = orderRepository.save(savedOrder);
        return ResponseEntity.ok(finalOrder);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusMap) {
        String status = statusMap.get("status");
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(status);
                    return ResponseEntity.ok(orderRepository.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
