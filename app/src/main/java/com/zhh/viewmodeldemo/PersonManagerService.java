package com.zhh.viewmodeldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhanghehe on 2019/3/29.
 * desc:
 */
public class PersonManagerService extends Service {
    public final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return personManagerAidl;
    }

    private final PersonManagerAidl.Stub personManagerAidl = new PersonManagerAidl.Stub() {
        @Override
        public void createPerson(Person p) throws RemoteException {
            Log.e(TAG, "invoking createPerson() method ,name is : " + p.mName);
        }

        @Override
        public void updatePerson(String name, Person p) throws RemoteException {
            Log.e(TAG, "invoking updatePerson() method ,origin name is : " + p.mName + ", new name is :" + name);
            p.mName = name;
        }

        @Override
        public void dropPerson(Person p) throws RemoteException {
            Log.e(TAG, "invoking dropPerson() method ,name is : " + p.mName);

        }
    };
}
