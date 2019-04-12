package com.zhh.viewmodeldemo.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by zhanghehe on 2019/4/3.
 * desc:
 */
public class Client {
    public static void main(String[] args) {
//        ProxySubject proxySubject = new ProxySubject(new RealSubject());
//        proxySubject.visit();

        Subject realSubject = new RealSubject();
        DynamicProxy proxy = new DynamicProxy(realSubject);
        ClassLoader classLoader = realSubject.getClass().getClassLoader();
        Subject subject = (Subject) Proxy.newProxyInstance(classLoader, realSubject.getClass().getInterfaces(), proxy);
        subject.visit();
    }
}
