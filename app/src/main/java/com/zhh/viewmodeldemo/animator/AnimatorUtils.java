package com.zhh.viewmodeldemo.animator;

import android.view.View;

/**
 * Created by zhanghehe on 2019/4/12.
 * desc:
 */
public class AnimatorUtils {
    private static AnimatorUtils instance;
    private View view;

    public static AnimatorUtils getInstance(){
        if (instance == null){
            instance = new AnimatorUtils();
        }
        return instance;
    }

    public void startAnimator(View view){
        this.view = view;

    }

    public void setHaha(){
//        view.setTranslationX();
//        view.setTranslationY();
    }
}
