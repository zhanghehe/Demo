package com.zhh.viewmodeldemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by zhanghehe on 2019/3/29.
 * desc:
 */
public class LiveDataTimerViewModel extends ViewModel {
    private static final int SECOND = 1000;
    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();

    private long initialTime;

    public LiveDataTimerViewModel() {
        initialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - initialTime);
                mElapsedTime.postValue(newValue);
            }
        }, SECOND, SECOND);
    }

    public LiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }
}
