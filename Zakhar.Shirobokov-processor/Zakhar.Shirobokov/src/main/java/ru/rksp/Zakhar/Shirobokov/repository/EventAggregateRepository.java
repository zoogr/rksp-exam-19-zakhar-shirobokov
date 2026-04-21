package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.rksp.Zakhar.Shirobokov.entity.EventAggregate;

@Repository
public interface EventAggregateRepository extends CrudRepository<EventAggregate, LocalDateTime> {
    // ClickHouse: базовые CRUD через Spring Data JDBC
}