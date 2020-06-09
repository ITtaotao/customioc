package com.ittaotao.module2.ioc;

import org.junit.Test;

public class Controller {

    private ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");
    private UserService userService = (UserService) proxyFactory.getCglibProxy(BeanFactory.getBean("userService"));

    @Test
    public void main() throws Exception {
        userService.transfer(2, 5, 100f);
    }
}
