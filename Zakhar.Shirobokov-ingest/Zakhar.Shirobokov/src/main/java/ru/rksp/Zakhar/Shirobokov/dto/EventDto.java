package Zakhar.Shirobokov.src.main.java.ru.rksp.Zakhar.Shirobokov.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Событие доставки")
public class EventDto {

    @Schema(description = "Идентификатор события", example = "evt_12345")
    @NotBlank(message = "ID обязателен")
    private String id;

    @Schema(description = "Отправитель", example = "sender@example.com")
    @NotBlank(message = "Отправитель обязателен")
    private String sender;

    @Schema(description = "Получатель", example = "recipient@example.com")
    @NotBlank(message = "Получатель обязателен")
    private String recipient;

    @Schema(description = "Адрес доставки", example = "г. Москва, ул. Примерная, д. 1")
    @NotBlank(message = "Адрес доставки обязателен")
    private String deliveryAddress;

    @Schema(description = "Статус доставки", example = "DELIVERED",
            allowableValues = {"PENDING", "IN_TRANSIT", "DELIVERED", "FAILED"})
    @NotBlank(message = "Статус обязателен")
    private String status;

    @Schema(description = "Дата и время события")
    @NotNull(message = "Дата события обязательна")
    private LocalDateTime eventDate;
}