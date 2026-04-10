package aston.homework.util;

import aston.homework.model.User;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.stereotype.Component;

@Getter
@Component
public class UserGenerator {

    private Model<User> userModel;

    private Faker faker;

    @PostConstruct
    private void init() {
        faker = new Faker();

        userModel = Instancio.of(User.class)
                .ignore(Select.field(User::getId))
                .ignore(Select.field(User::getCreatedAt))
                .supply(Select.field(User::getName), () -> faker.name().firstName())
                .supply(Select.field(User::getEmail), () -> faker.internet().emailAddress())
                .supply(Select.field(User::getAge), () -> (int) (90 * Math.random() + 10))
                .toModel();
    }
}
