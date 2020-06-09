package com.ittaotao.module2.ioc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author tao.liu
 * @desc 将JDBC 连接池绑定到 当前执行的线程
 */
public class ConnectionUtils {


    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>(); // 存储当前线程的连接

    public Connection getCurrentThreadConn() throws SQLException {

        Connection connection = threadLocal.get();
        if (connection == null) {
            connection = HikariUtils.getInstance().getConnection();
            // 绑定到当前线程
            threadLocal.set(connection);
        }
        return connection;

    }
}
