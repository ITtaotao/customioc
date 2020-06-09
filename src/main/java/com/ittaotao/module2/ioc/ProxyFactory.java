package com.ittaotao.module2.ioc;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    /**
     * Jdk动态代理
     *
     * @param obj 委托对象
     * @return 代理对象
     */
    public Object getJdkProxy(Object obj) {

        // 获取代理对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Object result = null;

                    try {
                        // 开启事务(关闭事务的自动提交)
                        transactionManager.beginTransaction();

                        result = method.invoke(obj, args);

                        // 提交事务

                        transactionManager.commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        // 回滚事务
                        transactionManager.rollback();

                        // 抛出异常便于上层servlet捕获
                        throw e;

                    }

                    return result;
                });

    }


    /**
     * 使用cglib动态代理生成代理对象
     *
     * @param obj 委托对象
     * @return
     */
    public Object getCglibProxy(Object obj) {
        return Enhancer.create(obj.getClass(), (MethodInterceptor) (o, method, objects, methodProxy) -> {
            Object result = null;
            try {
                // 开启事务(关闭事务的自动提交)
                transactionManager.beginTransaction();

                result = method.invoke(obj, objects);

                // 提交事务

                transactionManager.commit();
            } catch (Exception e) {
                // 回滚事务
                transactionManager.rollback();
                throw e;
            }
            return result;
        });
    }
}
