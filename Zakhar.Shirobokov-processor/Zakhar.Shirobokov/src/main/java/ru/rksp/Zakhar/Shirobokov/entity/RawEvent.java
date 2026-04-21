package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "raw_delivery_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawEvent {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String recipient;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String deliveryAddress;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime processedAt;
}