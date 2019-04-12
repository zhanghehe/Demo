package com.zhh.viewmodeldemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LiveDataTimerViewModel liveDataTimerViewModel;
    private TextView tv;
    private int x, y, rawx, rawy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        liveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);
        subscribe();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().add(R.id.container, new MainFragment(), "aaaaa").commit();*/

            }
        });
        Intent intent = new Intent();
        intent.setPackage(getPackageName());
        intent.setComponent(new ComponentName("com.zhh.viewmodeldemo", "com.zhh.viewmodeldemo.PersonManagerService"));

        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                PersonManagerAidl aidl = PersonManagerAidl.Stub.asInterface(service);
                try {
                    aidl.updatePerson("你你你你你", new Person("我我我我我我"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void subscribe() {
        final Observer<Long> longObserver = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {
                ((TextView) findViewById(R.id.tv)).setText(aLong.intValue() + "");
            }
        };
        liveDataTimerViewModel.getElapsedTime().observe(this, longObserver);
    }
}
