package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.sql.data.VerifyInfo;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public DashboardPage verifyByValid(VerifyInfo verifyInfo) {
        codeField.setValue(verifyInfo.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
