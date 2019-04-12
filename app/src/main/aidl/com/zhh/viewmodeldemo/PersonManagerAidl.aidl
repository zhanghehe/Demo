// PersonManagerAidl.aidl
package com.zhh.viewmodeldemo;

// Declare any non-default types here with import statements
import com.zhh.viewmodeldemo.Person;

interface PersonManagerAidl {
    void createPerson (in Person p);
    void updatePerson (String name ,inout Person p);
    void dropPerson (out Person p);
}
