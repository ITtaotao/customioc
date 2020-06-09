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
//        hikariDataSource.setConnectionTimeout(6000L);
//        hikariDataSource.setMaximumPoolSize(2);
        System.out.println("-----空闲时间"+hikariDataSource.getIdleTimeout());
        System.out.println("-----隔离级别"+hikariDataSource.getTransactionIsolation());
        System.out.println("-----连接超时时间"+hikariDataSource.getConnectionTimeout());

    }

    public static HikariDataSource getInstance() {
        return hikariDataSource;
    }

}
