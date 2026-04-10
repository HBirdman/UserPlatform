package aston.homework.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Пользователь с этим ID не найден: ID=" + id);
    }
}
