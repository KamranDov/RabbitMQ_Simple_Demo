package org.example.rabbitmqdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmqdemo.domain.Customer;
import org.example.rabbitmqdemo.domain.Notification;
import org.example.rabbitmqdemo.dto.NotificationRequest;
import org.example.rabbitmqdemo.repository.CustomerRepository;
import org.example.rabbitmqdemo.repository.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final CustomerRepository customerRepository;
    private final NotificationRepository notificationRepository;

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")

    public void receiveNotificationAndSendToCustomer(NotificationRequest notificationRequest) {
        log.info("Received message from RabbitMQ: {}", notificationRequest);

        Customer customer = customerRepository.findByEmail(notificationRequest.customerEmail()).orElseThrow(
                () -> {
                    log.error("Customer not found with email: {}", notificationRequest.customerEmail());
                    return new RuntimeException("Customer not found");
                });

        Notification notification = new Notification();
        notification.setMessage(notificationRequest.message());
        notification.setCustomer(customer);
        notificationRepository.save(notification);
        log.info("Notification sent to customer: {}", customer);
    }
}
