package org.example.rabbitmqdemo.controller;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.rabbitmqdemo.domain.Customer;
import org.example.rabbitmqdemo.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepo;


    @PostMapping
    public ResponseEntity<String> createCustomer() {
        Faker faker = new Faker();
        for (int customer = 0; customer < 30; customer++) {
            Customer newCustomer = Customer.builder()
                    .fullName(faker.name().fullName())
                    .phoneNumber(faker.phoneNumber().cellPhone())
                    .email(faker.internet().emailAddress())
                    .dateOfBirth(faker.date().birthday())
                    .build();
            customerRepo.save(newCustomer);
        }
        return ResponseEntity.status(201).body("Customer created successfully");
    }
}
