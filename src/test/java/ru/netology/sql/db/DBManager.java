package ru.netology.sql.db;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.sql.data.DBConnectionInfo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private final static DBConnectionInfo dbConnectionInfo = new DBConnectionInfo(
            "jdbc:mysql://192.168.99.100:3306/db",
            "user",
            "pass"
    );

    @SneakyThrows
    public static String getVerificationCodeByLogin(String login) {
        String userIdSQL = "SELECT id FROM users WHERE login = ?;";
        String codeSQL = "SELECT code FROM auth_codes WHERE created = (SELECT MAX(created) FROM auth_codes WHERE user_id = ?);";
        QueryRunner runner = new QueryRunner();
        try (
                Connection connection = DriverManager.getConnection(dbConnectionInfo.getUrl(), dbConnectionInfo.getUser(), dbConnectionInfo.getPassword())
        ) {
            String userid = runner.query(connection, userIdSQL, new ScalarHandler<>(), login);
            return runner.query(connection, codeSQL, new ScalarHandler<>(), userid);
        }
    }

    @SneakyThrows
    public static void clearDB() {
        String transactionsClearSQL = "DELETE FROM card_transactions;";
        String codesClearSQL = "DELETE FROM auth_codes;";
        String cardsClearSQL = "DELETE FROM cards;";
        String usersClearSQL = "DELETE FROM users;";
        QueryRunner runner = new QueryRunner();
        try (
                Connection connection = DriverManager.getConnection(dbConnectionInfo.getUrl(), dbConnectionInfo.getUser(), dbConnectionInfo.getPassword())
        ) {
            runner.update(connection, transactionsClearSQL);
            runner.update(connection, codesClearSQL);
            runner.update(connection, cardsClearSQL);
            runner.update(connection, usersClearSQL);
        }
    }
}