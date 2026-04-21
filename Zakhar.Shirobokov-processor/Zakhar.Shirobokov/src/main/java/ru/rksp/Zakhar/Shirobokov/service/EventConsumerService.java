package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rksp.Zakhar.Shirobokov.dto.EventDto;
import ru.rksp.Zakhar.Shirobokov.entity.RawEvent;
import ru.rksp.Zakhar.Shirobokov.repository.RawEventRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventConsumerService {

    private final RawEventRepository rawEventRepository;

    @Transactional
    public void processEvent(EventDto eventDto) {
        // Проверка на дубликаты
        if (rawEventRepository.existsById(eventDto.getId())) {
            log.warn("Duplicate event detected: {}", eventDto.getId());
            return;
        }

        // Маппинг DTO → Entity
        RawEvent event = new RawEvent();
        event.setId(eventDto.getId());
        event.setSender(eventDto.getSender());
        event.setRecipient(eventDto.getRecipient());
        event.setDeliveryAddress(eventDto.getDeliveryAddress());
        event.setStatus(eventDto.getStatus());
        event.setEventDate(eventDto.getEventDate());
        event.setProcessedAt(java.time.LocalDateTime.now());

        rawEventRepository.save(event);
        log.info("Event {} saved to PostgreSQL", event.getId());
    }
}