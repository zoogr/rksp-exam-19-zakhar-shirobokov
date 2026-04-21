-- Создание таблицы для агрегатов в ClickHouse

CREATE TABLE IF NOT EXISTS delivery_event_aggregates (
    date_time_recorded DateTime64(3) DEFAULT now64(3),
    record_count UInt64,
    ingestion_timestamp DateTime64(3) DEFAULT now64(3)
) ENGINE = MergeTree()
PARTITION BY toYYYYMM(date_time_recorded)
ORDER BY (date_time_recorded)
TTL date_time_recorded + INTERVAL 1 YEAR
SETTINGS index_granularity = 8192;


-- Таблица хранит агрегированные данные: количество записей, зафиксированных в момент времени