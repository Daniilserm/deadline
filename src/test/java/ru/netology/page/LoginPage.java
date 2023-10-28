package ru.netology.page;

import ru.netology.data.DataHelper;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public static VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }

    public void findErrorMessage(String expectedText) {
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(exactText(expectedText)).shouldBe(visible);
    }
}