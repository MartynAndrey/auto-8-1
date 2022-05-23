package ru.netology.sql.data;

import lombok.Value;

@Value
public class DBConnectionInfo {
    private String url;
    private String user;
    private String password;
}
