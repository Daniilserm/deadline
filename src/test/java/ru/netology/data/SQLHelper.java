package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.openqa.selenium.safari.ConnectionClosedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {

    }

    private static Connection getConnect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.VerificationCode getVerificationCodeFor() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created  DESC LIMIT 1";
        var connect = getConnect();
        var code = runner.query(connect, codeSQL, new ScalarHandler<String>());
        return new DataHelper.VerificationCode(code);
    }

    @SneakyThrows
    public static void cleanData() {
        var connection = getConnect();
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }

    @SneakyThrows
    public static void cleanCodes() {
        var connection = getConnect();
        runner.execute(connection, "DELETE FROM auth_codes");
    }

}
