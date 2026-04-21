package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rksp.Zakhar.Shirobokov.dto.EventDto;
import ru.rksp.Zakhar.Shirobokov.service.EventProducerService;

@Slf4j
@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Event Ingest", description = "API для приёма событий доставки")
@RequiredArgsConstructor
public class EventIngestController {

    private final EventProducerService eventProducerService;

    @PostMapping
    @Operation(
            summary = "Отправить событие в систему",
            description = "Принимает событие доставки и отправляет его в очередь RabbitMQ для дальнейшей обработки",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Событие принято и отправлено в очередь"),
                    @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")
            }
    )
    public ResponseEntity<Void> ingestEvent(@RequestBody @Valid EventDto event) {
        log.info("Received event ingestion request: {}", event.getId());
        eventProducerService.sendEventToQueue(event);
        return ResponseEntity.accepted().build();
    }
}