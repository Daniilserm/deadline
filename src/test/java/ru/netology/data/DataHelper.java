package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfoValid() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

       private static String generateLogin() {
        return faker.name().username();
    }

    private static String generatePassword() {
        return faker.internet().password();
    }

    public static AuthInfo generateUser() {
        return new AuthInfo(generateLogin(), generatePassword());
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode generateVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode(faker.numerify("######"));
    }

}
