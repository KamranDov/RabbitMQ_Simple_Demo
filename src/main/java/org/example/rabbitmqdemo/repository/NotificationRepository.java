package org.example.rabbitmqdemo.repository;

import org.example.rabbitmqdemo.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByMessage(String message);
}
