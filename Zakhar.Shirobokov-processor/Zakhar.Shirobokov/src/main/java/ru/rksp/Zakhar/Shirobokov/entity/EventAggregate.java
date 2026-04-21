package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("delivery_event_aggregates")
public class EventAggregate {

    @Id
    private LocalDateTime dateTimeRecorded;

    private Long recordCount;

    private LocalDateTime ingestionTimestamp;
}