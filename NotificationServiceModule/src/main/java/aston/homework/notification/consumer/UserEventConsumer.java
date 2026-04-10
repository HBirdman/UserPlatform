package aston.homework.notification.consumer;

import aston.homework.notification.event.UserEvent;
import aston.homework.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "${app.kafka.topic.user-events}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleUserEvent(UserEvent event) {
        log.info("Получено событие из Kafka: {}", event);

        switch (event.getType()) {
            case CREATED:
                emailService.sendUserCreatedNotification(event.getEmail());
                break;
            case DELETED:
                emailService.sendUserDeletedNotification(event.getEmail());
                break;
            default:
                log.warn("Неизвестный тип события: {}", event.getType());
        }
    }
}
