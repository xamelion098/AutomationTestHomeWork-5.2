package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;


import java.util.Locale;

class DataGenerator {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    private static void sendRequest(RegistrationDto user) {
    }

    public static String getRandomLogin() {
        return faker.name().username();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto getUser(String status) {
            return new RegistrationDto(getRandomLogin(), getRandomPassword(), status);
        }

        public static RegistrationDto getRegisteredUser(String status) {
            return ApiSupport.sendRequest(getUser(status));
        }
    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }
}


