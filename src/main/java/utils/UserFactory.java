package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactory {

    static Faker faker = new Faker();

    public static User positiveUser() {
        return User.builder()
                .username(faker.internet().emailAddress())
                .password(PropertiesReader.getProperty
                        ("base.properties", "password_for_registration"))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }
}
