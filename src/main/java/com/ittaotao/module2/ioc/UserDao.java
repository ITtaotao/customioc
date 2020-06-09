package com.ittaotao.module2.ioc;

public interface UserDao {
    User queryUserById(int id) throws Exception;

    int updateMoneyById(User user) throws Exception;
}
