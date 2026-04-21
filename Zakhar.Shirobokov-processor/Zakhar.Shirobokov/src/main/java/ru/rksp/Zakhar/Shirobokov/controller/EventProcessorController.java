package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rksp.Zakhar.Shirobokov.service.CountService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Event Processor", description = "API для обработки и агрегации событий")
@RequiredArgsConstructor
public class EventProcessorController {

    private final CountService countService;

    @PostMapping("/count")
    @Operation(
            summary = "Получить количество событий и сохранить агрегат",
            description = "Возвращает количество записей в PostgreSQL и сохраняет агрегированные данные в ClickHouse",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ с количеством"),
                    @ApiResponse(responseCode = "500", description = "Ошибка при записи в ClickHouse")
            }
    )
    public ResponseEntity<Map<String, Long>> getCountAndAggregate() {
        log.info("Received count request");

        long count = countService.getEventsCount();
        countService.saveCountToClickHouse(count);

        log.info("Count: {}, saved to ClickHouse", count);
        return ResponseEntity.ok(Map.of("count", count));
    }
}