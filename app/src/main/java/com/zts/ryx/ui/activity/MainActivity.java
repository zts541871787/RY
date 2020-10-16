package com.zts.ryx.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zts.ryx.R;
import com.zts.ryx.utils.SystemUI;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SystemUI.fixSystemUI(this);
        getSupportActionBar().hide();

    }
}
