package com.ittaotao.module2.ioc;

import com.zaxxer.hikari.HikariDataSource;

public class HikariUtils {

    private HikariUtils() {
    }

    private static HikariDataSource hikariDataSource = new HikariDataSource();


    static {
        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/vertx?useSSL=false");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        hikariDataSource.setMaxLifetime(1000 * 60 * 10);
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setValidationTimeout(1000 * 60);
        hikariDataSource.setIdleTimeout(1000*60*5);
    }

    public static HikariDataSource getInstance() {
        return hikariDataSource;
    }

}
