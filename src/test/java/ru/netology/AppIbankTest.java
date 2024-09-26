package ru.netology;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.DataGenerator.Registration.getUser;
import static ru.netology.DataGenerator.getRandomLogin;
import static ru.netology.DataGenerator.getRandomPassword;

class AppIbankTest {

    @Test
    public void validTestAppIbank() {

        var registeredUser = getRegisteredUser("active");

        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("[data-test-id=password] input").setValue(registeredUser.getPassword());
        $(".button__content").click();
        $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(visible);


    }

    @Test
    public void inValidTestAppIbank() {

        var registeredUser = getUser("active");

        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("[data-test-id=password] input").setValue(registeredUser.getPassword());
        $(".button__content").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(visible);
    }

    @Test
    public void inValidBlockUserTestAppIbank() {

        var blockedUser = getRegisteredUser("blocked");

        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(blockedUser.getLogin());
        $("[data-test-id=password] input").setValue(blockedUser.getPassword());
        $(".button__content").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"))
                .shouldBe(visible);

    }

    @Test
    public void inValidLoginTestAppIbank() {

        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();
        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(wrongLogin);
        $("[data-test-id=password] input").setValue(registeredUser.getPassword());
        $(".button__content").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(visible);

    }

    @Test
    public void inValidPasswordTestAppIbank() {

        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();
        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("[data-test-id=password] input").setValue(wrongPassword);
        $(".button__content").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(visible);

    }
}
