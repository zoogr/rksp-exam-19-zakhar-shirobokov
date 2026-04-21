-- Создание таблицы для сырых событий доставки в PostgreSQL

CREATE TABLE IF NOT EXISTS raw_delivery_events (
    id VARCHAR(255) PRIMARY KEY,
    sender VARCHAR(255) NOT NULL,
    recipient VARCHAR(255) NOT NULL,
    delivery_address TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    event_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    processed_at TIMESTAMP WITH TIME ZONE
);

-- Индексы для ускорения поиска
CREATE INDEX IF NOT EXISTS idx_raw_events_status ON raw_delivery_events(status);
CREATE INDEX IF NOT EXISTS idx_raw_events_date ON raw_delivery_events(event_date DESC);
CREATE INDEX IF NOT EXISTS idx_raw_events_sender ON raw_delivery_events(sender);


COMMENT ON TABLE raw_delivery_events IS 'Сырые события доставки из RabbitMQ';
COMMENT ON COLUMN raw_delivery_events.id IS 'Уникальный идентификатор события';
COMMENT ON COLUMN raw_delivery_events.sender IS 'Отправитель события';
COMMENT ON COLUMN raw_delivery_events.recipient IS 'Получатель события';
COMMENT ON COLUMN raw_delivery_events.delivery_address IS 'Адрес доставки';
COMMENT ON COLUMN raw_delivery_events.status IS 'Статус доставки: PENDING, IN_TRANSIT, DELIVERED, FAILED';
COMMENT ON COLUMN raw_delivery_events.event_date IS 'Дата и время события';