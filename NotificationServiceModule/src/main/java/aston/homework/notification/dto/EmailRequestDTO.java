package aston.homework.notification.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class EmailRequestDTO {

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String to;

    @NotBlank(message = "Тема не может быть пустой")
    private String subject;

    @NotBlank(message = "Сообщение не может быть пустым")
    private String body;
}
