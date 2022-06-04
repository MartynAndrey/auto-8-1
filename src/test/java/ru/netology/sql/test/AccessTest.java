package ru.netology.sql.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.netology.sql.data.AuthHelper;
import ru.netology.sql.db.DBManager;
import ru.netology.sql.page.DashboardPage;
import ru.netology.sql.page.LoginPage;
import ru.netology.sql.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccessTest {
    private final String accessAllowedHeader = "Личный кабинет";
    private final String accessBlockedMessage = "Система заблокирована";

    @Test
    public void shouldAccessAllowed() {
        open("http://localhost:9999/");
        AuthHelper authHelper = new AuthHelper();
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.loginByValid(authHelper.getValidAuthInfo());

        String dbCode = DBManager.getVerificationCodeByLogin(authHelper.getValidAuthInfo().getLogin());
        AuthHelper.VerifyInfo verifyInfo = new AuthHelper().new VerifyInfo(dbCode);

        DashboardPage dashboardPage = verificationPage.verifyByValid(verifyInfo);

        String actual = dashboardPage.getHeader();
        String expected = this.accessAllowedHeader;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldAccessBlocked() {
        open("http://localhost:9999/");
        AuthHelper authHelper = new AuthHelper();
        LoginPage loginPage = new LoginPage();
        String actualMessage = loginPage.tripleLoginByInvalid(authHelper.getInvalidAuthInfo());

        assertEquals(this.accessBlockedMessage, actualMessage);
    }

    @AfterAll
    public void clear() {
        DBManager.clearDB();
    }
}