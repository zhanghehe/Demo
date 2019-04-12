package com.zhh.viewmodeldemo.proxy;

/**
 * Created by zhanghehe on 2019/4/3.
 * desc:
 */
public class ProxySubject implements Subject {
    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void visit() {
        subject.visit();
    }
}
