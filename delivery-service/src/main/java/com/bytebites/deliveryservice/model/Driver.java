package com.bytebites.deliveryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    private String id;
    private String name;
    private int age;
    private String phone;
    private String address;
    private String vehicleNo;
    private double rating;
    private String workingHours;
    private int experienceYears;
    private String status; // AVAILABLE, ON_DELIVERY, OFFLINE
    private String imageUrl;

    public Driver() {}

    public Driver(String id, String name, int age, String phone, String address, String vehicleNo, double rating, String workingHours, int experienceYears, String status, String imageUrl) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.vehicleNo = vehicleNo;
        this.rating = rating;
        this.workingHours = workingHours;
        this.experienceYears = experienceYears;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getVehicleNo() { return vehicleNo; }
    public void setVehicleNo(String vehicleNo) { this.vehicleNo = vehicleNo; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getWorkingHours() { return workingHours; }
    public void setWorkingHours(String workingHours) { this.workingHours = workingHours; }
    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
