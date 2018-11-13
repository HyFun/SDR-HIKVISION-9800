package com.sdr.hkvideo;

import android.os.Bundle;

import com.sdr.hklibrary.HKVideoLibrary;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HKVideoLibrary.getInstance().start(getContext(), "60.191.94.170:8086", "admin", "Hik12345");
    }
}
