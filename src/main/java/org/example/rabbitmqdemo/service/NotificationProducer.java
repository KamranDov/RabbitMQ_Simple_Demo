package org.example.rabbitmqdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmqdemo.domain.Customer;
import org.example.rabbitmqdemo.domain.Notification;
import org.example.rabbitmqdemo.dto.NotificationRequest;
import org.example.rabbitmqdemo.repository.CustomerRepository;
import org.example.rabbitmqdemo.repository.NotificationRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(NotificationRequest request) {
        rabbitTemplate.convertAndSend(request);
        log.info("Sent message to RabbitMQ: {}", request);

    }
}
