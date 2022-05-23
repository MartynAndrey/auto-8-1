package ru.netology.sql.test;

import org.junit.jupiter.api.*;
import ru.netology.sql.data.*;
import ru.netology.sql.db.DBManager;
import ru.netology.sql.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccessTest {
    private final String accessAllowedHeader = "Личный кабинет";
    private final String accessBlockedMessage = "Система заблокирована";
    private final AuthInfo validAuthInfo = new AuthInfo("vasya", "qwerty123");
    private final AuthInfo invalidAuthInfo = new AuthInfo("petya", "qwerty098");
    private final DBConnectionInfo dbConnectionInfo = new DBConnectionInfo(
            "jdbc:mysql://192.168.99.100:3306/db",
            "user",
            "pass"
    );

    @Test
    public void shouldAccessAllowed() {
        open("http://localhost:9999/");
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.loginByValid(this.validAuthInfo);

        DBManager dbManager = new DBManager();
        String dbCode = dbManager.getVerificationCodeByLogin(this.dbConnectionInfo, this.validAuthInfo.getLogin());
        VerifyInfo verifyInfo = new VerifyInfo(dbCode);

        DashboardPage dashboardPage = verificationPage.verifyByValid(verifyInfo);

        String actual = dashboardPage.getHeader();
        String expected = this.accessAllowedHeader;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldAccessBlocked() {
        open("http://localhost:9999/");
        LoginPage loginPage = new LoginPage();
        String actualMessage = loginPage.tripleLoginByInvalid(this.invalidAuthInfo);

        assertEquals(this.accessBlockedMessage, actualMessage);
    }

    @AfterAll
    public void clear() {
        DBManager dbManager = new DBManager();
        dbManager.clearDB(this.dbConnectionInfo);
    }
}