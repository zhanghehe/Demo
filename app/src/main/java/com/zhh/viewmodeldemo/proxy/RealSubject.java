package com.zhh.viewmodeldemo.proxy;

/**
 * Created by zhanghehe on 2019/4/3.
 * desc:
 */
public class RealSubject implements Subject {
    @Override
    public void visit() {
        System.out.println("real_subject");
    }
}
