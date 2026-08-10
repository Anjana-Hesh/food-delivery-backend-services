package com.bytebites.deliveryservice.controller;

import com.bytebites.deliveryservice.model.Driver;
import com.bytebites.deliveryservice.repository.DriverRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DriverRepository driverRepository;

    @PostConstruct
    public void init() {
        if (driverRepository.count() == 0) {
            driverRepository.save(new Driver("DRV-101", "Kamal Perera", 29, "+94 77 123 4567", "123, Galle Road, Colombo 03", "WP BAZ-4829", 4.8, "08:00 AM - 05:00 PM", 4, "AVAILABLE", "https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=500&q=80"));
            driverRepository.save(new Driver("DRV-102", "Sunil Shantha", 34, "+94 71 987 6543", "45, Kandy Road, Kadawatha", "WP CAD-1102", 4.5, "10:00 AM - 07:00 PM", 6, "ON_DELIVERY", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=500&q=80"));
            driverRepository.save(new Driver("DRV-103", "Nimal Silva", 31, "+94 75 444 8899", "88, Main Street, Galle", "WP GAE-5521", 4.9, "07:00 AM - 04:00 PM", 5, "AVAILABLE", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=500&q=80"));
            driverRepository.save(new Driver("DRV-104", "Ruwan Kumara", 27, "+94 72 333 1122", "12, Beach Road, Matara", "WP SP-9988", 4.6, "09:00 AM - 06:00 PM", 3, "AVAILABLE", "https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?auto=format&fit=crop&w=500&q=80"));
            driverRepository.save(new Driver("DRV-105", "Kasun Jayasuriya", 33, "+94 76 555 4433", "67, Lake Road, Kurunegala", "WP CP-1234", 4.7, "08:00 AM - 05:00 PM", 7, "AVAILABLE", "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?auto=format&fit=crop&w=500&q=80"));
            System.out.println("Prepopulated 5 default drivers in PostgreSQL");
        }
    }

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @PostMapping("/drivers")
    public Driver registerDriver(@RequestBody Driver driver) {
        if (driver.getId() == null || driver.getId().isEmpty()) {
            driver.setId("DRV-" + (100 + driverRepository.count() + 1));
        }
        if (driver.getStatus() == null) {
            driver.setStatus("AVAILABLE");
        }
        if (driver.getImageUrl() == null || driver.getImageUrl().isEmpty()) {
            driver.setImageUrl("https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=500&q=80");
        }
        return driverRepository.save(driver);
    }

    @PutMapping("/drivers/{id}/status")
    public ResponseEntity<Driver> updateStatus(@PathVariable String id, @RequestBody Map<String, String> statusMap) {
        String status = statusMap.get("status");
        return driverRepository.findById(id)
                .map(driver -> {
                    driver.setStatus(status);
                    return ResponseEntity.ok(driverRepository.save(driver));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
