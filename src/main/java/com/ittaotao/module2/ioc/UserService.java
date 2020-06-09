package com.ittaotao.module2.ioc;

public class UserService {

    private UserDao userDao;

    /**
     * @param accountDao
     * @desc 使用 setter getter 注入
     */
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
