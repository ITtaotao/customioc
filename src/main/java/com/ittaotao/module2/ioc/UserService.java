package com.ittaotao.module2.ioc;

public class UserService {
    // 最佳状态
    private UserDao userDao;

    // 构造函数传值/set方法传值

    public void setUserDao(UserDao accountDao) {
        this.userDao = accountDao;
    }


    public void transfer(int id, int toId, Float money) throws Exception {


        User from = userDao.queryUserById(id);
        User to = userDao.queryUserById(toId);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        userDao.updateMoneyById(to);
        int c = 1 / 0;
        userDao.updateMoneyById(from);

    }
}
