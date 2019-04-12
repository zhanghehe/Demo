package com.zhh.viewmodeldemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * Created by zhanghehe on 2019/3/29.
 * desc:
 */
public class MainFragment extends Fragment {
    private SeekBar sb1, sb2;
private SeekBarViewModel model;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step5, container, false);
        sb1 = root.findViewById(R.id.sb1);
        sb2 = root.findViewById(R.id.sb2);
        subscribeSeekBar();
        return root;
    }

    private void subscribeSeekBar() {
        model = ViewModelProviders.of(this).get(SeekBarViewModel.class);
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                model.getOValue().setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        model.getOValue().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                sb2.setProgress(integer.intValue());
            }
        });
    }


}
