package org.example.rabbitmqdemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.rabbitmqdemo.dto.NotificationRequest;
import org.example.rabbitmqdemo.service.NotificationConsumer;
import org.example.rabbitmqdemo.service.NotificationProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationProducer notificationProducer;

    @PostMapping("send-notification")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        notificationProducer.sendNotification(request);
        return ResponseEntity.status(200).body("Notification sent successfully");
    }

}
