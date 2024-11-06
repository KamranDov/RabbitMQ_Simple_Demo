package org.example.rabbitmqdemo.dto;

public record NotificationRequest(
    String message,
    String customerEmail
) {
}
