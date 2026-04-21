package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.rksp.Zakhar.Shirobokov.repository.RawEventRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountService {

    private final RawEventRepository rawEventRepository;
    private final JdbcTemplate clickHouseJdbcTemplate;

    public long getEventsCount() {
        return rawEventRepository.countAllEvents();
    }

    public void saveCountToClickHouse(long count) {
        String sql = "INSERT INTO delivery_event_aggregates (date_time_recorded, record_count, ingestion_timestamp) VALUES (?, ?, ?)";

        try {
            clickHouseJdbcTemplate.update(sql,
                    LocalDateTime.now(),
                    count,
                    LocalDateTime.now());
            log.info("Count {} saved to ClickHouse", count);
        } catch (Exception e) {
            log.error("Failed to save count to ClickHouse", e);
            throw new RuntimeException("ClickHouse write failed", e);
        }
    }
}