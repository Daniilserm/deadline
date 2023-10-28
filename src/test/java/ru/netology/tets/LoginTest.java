package ru.netology.tets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    LoginPage loginPage;

    @AfterEach
    void clean() {
        SQLHelper.cleanCodes();
    }

    @AfterAll
    static void cleanAll() {
        SQLHelper.cleanData();
    }

    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    void shouldLogin() {
        var authInfo = DataHelper.getAuthInfoValid();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.VerificationPage();
        var verificationCode = SQLHelper.getVerificationCodeFor();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldNotLogin() {
        var authInfo = DataHelper.generateUser();
        loginPage.validLogin(authInfo);
        loginPage.findErrorMessage("Ошибка! \nНеверно указан логин или пароль");
    }

    @Test
    void shouldErrorMessageVerify() {
        var authInfo = DataHelper.getAuthInfoValid();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.VerificationPage();
        var verificationCode = DataHelper.generateVerificationCodeFor(authInfo);
        verificationPage.verify(verificationCode.getCode());
        verificationPage.findErrorMessage("Ошибка! \nНеверно указан код! Попробуйте ещё раз.");
    }

}
