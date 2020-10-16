package com.zts.ryx.manager;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;

import com.zts.ryx.utils.LogUtils;

import java.io.IOException;

//TODO 音乐播放器工具类
public class MusicManager {
    public static final int Music_Start=0;
    public static final int Music_Pause=1;
    public static final int Music_Stop=2;

    public int Music_Play_Type=Music_Stop;//TODO 当前播放器状态

    private MediaPlayer mMediaPlayer;
    private static final int H_PROGRESS = 1000;
    private OnMusicProgressListener musicProgressListener;

    /**
     * 计算歌曲的进度：
     * 1.开始播放的时候就开启循环计算时长
     * 2.将进度计算结果对外抛出
     */
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case H_PROGRESS:
                    if (musicProgressListener != null) {
                        //拿到当前时长
                        int currentPosition = getCurrentPosition();
                        int pos = (int) (((float) currentPosition) / ((float) getDuration()) * 100);
                        musicProgressListener.OnProgress(currentPosition, pos);
                        mHandler.sendEmptyMessageDelayed(H_PROGRESS, 1000);
                    }
                    break;
            }
            return false;
        }
    });


    public MusicManager() {
        mMediaPlayer = new MediaPlayer();
    }

    //TODO 判断是否在播放
    public boolean isPlaying(){
        return mMediaPlayer.isPlaying();
    }

    public void  setLooping(boolean isLooping){
        mMediaPlayer.setLooping(isLooping);
    }


    //TODO 获取当前位置
    public int getCurrentPosition() {
       return  mMediaPlayer.getCurrentPosition();
    }

    //TODO 获取当前位置
    public int getDuration() {
        return  mMediaPlayer.getDuration();
    }

   //TODO  开始播放
    public void startPlay(AssetFileDescriptor path) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path.getFileDescriptor(),
                    path.getStartOffset(), path.getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            Music_Play_Type = Music_Start;
            mHandler.sendEmptyMessage(H_PROGRESS);
        } catch (IOException e) {
            LogUtils.e(e.toString());
            e.printStackTrace();
        }
    }

    //TODO  开始播放
    public void startPlay(String path) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            Music_Play_Type= Music_Start;
            mHandler.sendEmptyMessage(H_PROGRESS);
        } catch (IOException e) {
            LogUtils.e(e.toString());
            e.printStackTrace();
        }
    }


    public interface OnMusicProgressListener {
        void OnProgress(int progress, int pos);
    }

    public void stop(){
        mMediaPlayer.stop();
    }

}
