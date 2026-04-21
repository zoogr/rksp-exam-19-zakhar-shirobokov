package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.rksp.Zakhar.Shirobokov.entity.RawEvent;

@Repository
public interface RawEventRepository extends JpaRepository<RawEvent, String> {

    @Query("SELECT COUNT(e) FROM RawEvent e")
    long countAllEvents();

    boolean existsById(String id);
}