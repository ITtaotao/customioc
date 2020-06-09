package com.ittaotao.module2.ioc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {


    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }


    @Override
    public User queryUserById(int id) throws SQLException {
        //从连接池获取连接
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "select * from users where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        User account = new User();
        while (resultSet.next()) {
            account.setId(resultSet.getInt("id"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getFloat("money"));
        }

        return account;
    }

    @Override
    public int updateMoneyById(User account) throws Exception {

        // 从连接池获取连接
        // 改造为：从当前线程当中获取绑定的connection连接
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "update users set money=? where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setFloat(1, account.getMoney());
        preparedStatement.setInt(2, account.getId());
        int i = preparedStatement.executeUpdate();
        return i;
    }


}
