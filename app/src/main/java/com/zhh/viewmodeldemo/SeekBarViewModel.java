package com.zhh.viewmodeldemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by zhanghehe on 2019/3/29.
 * desc:
 */
public class SeekBarViewModel extends ViewModel {
    public MutableLiveData<Integer> getOValue() {
        return mValue;
    }

    private MutableLiveData<Integer> mValue = new MutableLiveData<>();

    public Integer getValue() {
        return mValue.getValue();
    }
}
