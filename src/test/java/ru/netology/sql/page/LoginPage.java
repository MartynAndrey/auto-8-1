package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.data.AuthInfo;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorMessage = $("[data-test-id=error-notification] div.notification__content");
    private SelenideElement errorButton = $("[data-test-id=error-notification] button.icon-button");

    public LoginPage() {
        loginField.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public VerificationPage loginByValid (AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public String tripleLoginByInvalid (AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        errorButton.click();
        loginButton.click();
        errorButton.click();
        loginButton.click();
        String message = errorMessage.getText().trim();
        errorButton.click();
        return message;
    }
}
