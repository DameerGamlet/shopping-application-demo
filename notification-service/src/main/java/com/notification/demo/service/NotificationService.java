package com.notification.demo.service;

import com.order.demo.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final MailSenderService mailSenderService;

    @KafkaListener(topics = "order-placed-send-message", groupId = "notification-group")
    public void listen(OrderPlacedEvent event) {
        log.info("LISTENER.INFO: Получено сообщение из топика 'order-placed-send-message' с содержанием {}", event);

        mailSenderService.mailSend(event);
    }
}
