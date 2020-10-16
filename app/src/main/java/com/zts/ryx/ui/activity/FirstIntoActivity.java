package com.zts.ryx.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zts.ryx.R;
import com.zts.ryx.manager.MusicManager;
import com.zts.ryx.utils.LogUtils;
import com.zts.ryx.utils.SystemUI;
import com.zts.ryx.utils.TimeUtil;

import io.rong.common.SystemUtils;

public class FirstIntoActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mMViewPager; // TODO  引导页vp
    private ImageView mIvMusicSwitch;//TODO 音乐按钮
    private TextView mTvGuideSkip; //TODO 跳过text
    private ImageView mIvGuidePoint1; //TODO 三个bananer按钮
    private ImageView mIvGuidePoint2;
    private ImageView mIvGuidePoint3;
    private MusicManager musicManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_into);
        getSupportActionBar().hide();
        SystemUI.fixSystemUI(this);
        initView();

    }


    //TODO 初始化控件
    public void initView() {
        mMViewPager = findViewById(R.id.mViewPager);
        mIvMusicSwitch = findViewById(R.id.iv_music_switch);
        mTvGuideSkip = findViewById(R.id.tv_guide_skip);
        mIvGuidePoint1 = findViewById(R.id.iv_guide_point_1);
        mIvGuidePoint2 = findViewById(R.id.iv_guide_point_2);
        mIvGuidePoint3 = findViewById(R.id.iv_guide_point_3);

        mIvGuidePoint1.setOnClickListener(this);
        mIvGuidePoint2.setOnClickListener(this);
        mIvGuidePoint3.setOnClickListener(this);

        startMusic();

        LogUtils.e(TimeUtil.transferTimer(System.currentTimeMillis()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_guide_point_1:
                selet(0);
                break;
            case R.id.iv_guide_point_2:
                selet(1);
                break;
            case R.id.iv_guide_point_3:
                selet(2);
                break;

        }
    }

    //TODO bananer小圆点点击方法
    public void selet(int num){
        mIvGuidePoint1.setImageResource(R.mipmap.img_guide_point);
        mIvGuidePoint2.setImageResource(R.mipmap.img_guide_point);
        mIvGuidePoint3.setImageResource(R.mipmap.img_guide_point);
        switch (num){
            case 0:
                mIvGuidePoint1.setImageResource(R.mipmap.img_guide_point_p);
                break;
            case 1:
                mIvGuidePoint2.setImageResource(R.mipmap.img_guide_point_p);
                break;
            case 2:
                mIvGuidePoint3.setImageResource(R.mipmap.img_guide_point_p);
                break;
        }
    }

    //TODO 播放音乐的方法
    public void startMusic(){
        musicManager = new MusicManager();
        musicManager.setLooping(true);
        AssetFileDescriptor music=getResources().openRawResourceFd(R.raw.music);
        musicManager.startPlay(music);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicManager.stop();
    }
}

