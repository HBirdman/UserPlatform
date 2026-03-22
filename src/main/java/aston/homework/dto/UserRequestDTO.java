package aston.homework.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 20, message = "Имя должно содержать от 2 до 20 символов")
    private String name;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный формат email")
    private String email;

    @Min(value = 0, message = "Возраст должен быть положительным")
    @Max(value = 120, message = "Возраст не может быть больше 120")
    private int age;
}
