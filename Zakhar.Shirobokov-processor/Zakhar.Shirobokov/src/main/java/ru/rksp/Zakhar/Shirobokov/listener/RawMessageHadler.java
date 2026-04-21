package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.listener;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import ru.rksp.Zakhar.Shirobokov.dto.EventDto;
import ru.rksp.Zakhar.Shirobokov.service.EventConsumerService;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class RawMessageHandler {

    private final EventConsumerService consumerService;

    @RabbitListener(queues = "${spring.rabbitmq.listener.simple.queues:events.raw}")
    public void handleMessage(
            EventDto event,
            @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
            Channel channel) {

        try {
            log.info("Processing event: {}", event.getId());
            consumerService.processEvent(event);
            channel.basicAck(deliveryTag, false);
            log.debug("Event {} processed and acknowledged", event.getId());
        } catch (Exception e) {
            log.error("Failed to process event {}: {}", event.getId(), e.getMessage());
            try {
                // Отправляем в DLQ после 3 попыток (логика в сервисе)
                channel.basicNack(deliveryTag, false, false);
            } catch (IOException ex) {
                log.error("Failed to nack message", ex);
            }
        }
    }
}