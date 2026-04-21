package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.rksp.Zakhar.Shirobokov.config.RabbitConfig;
import ru.rksp.Zakhar.Shirobokov.dto.EventDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProducerService {

    private final RabbitTemplate rabbitTemplate;

    public void sendEventToQueue(EventDto event) {
        log.info("Sending event to queue {}: {}", RabbitConfig.EVENTS_RAW_QUEUE, event.getId());
        rabbitTemplate.convertAndSend(RabbitConfig.EVENTS_RAW_QUEUE, event);
        log.debug("Event {} sent successfully", event.getId());
    }
}