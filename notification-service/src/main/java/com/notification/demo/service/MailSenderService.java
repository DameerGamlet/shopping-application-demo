package com.notification.demo.service;

import com.order.demo.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender mailSender;
    private static final String EMAIL_FROM = "springshop@email.com";

    public void mailSend(OrderPlacedEvent event) {
        final MimeMessagePreparator preparation = mimeMessage -> {
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(EMAIL_FROM);
            helper.setTo(event.getEmail());
            helper.setSubject(String.format("Ваш заказ с номером заказа %s успешно размещен.", event.getOrderNumber()));
            helper.setText(String.format("""
                            Привет, %s, %s
                            
                            Ваш заказ с номером заказа %s успешно размещен.
                            
                            С наилучшими пожеланиями
                            Весенний магазин
                            """,
                    event.getFirstName(),
                    event.getLastName(),
                    event.getOrderNumber()));
        };
        try {
            mailSender.send(preparation);
            log.info("Сообщение из order сервиса отправлен!!");
        } catch (MailException e) {
            log.error("Исключение произошло при отправке почты", e);
            throw new RuntimeException("Исключение произошло при отправке почты на адрес Springshop@email.com.", e);
        }
    }
}
