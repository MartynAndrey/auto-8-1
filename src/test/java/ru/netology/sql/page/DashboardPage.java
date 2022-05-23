package ru.netology.sql.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement dashboardHeader = $("[data-test-id=dashboard]");

    public DashboardPage() {
        dashboardHeader.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public String getHeader() {
        return dashboardHeader.getText().trim();
    }
}
