package com.sdr.hklibrary.constant;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public interface HKConstants {
    String HK_TAG = "海康视频TAG";
    String HIK_HISTORY = "HIK_HISTORY";

    interface Resource {
        /**
         * 控制中心
         */
        int TYPE_CTRL_UNIT = 1;
        /**
         * 区域
         */
        int TYPE_REGION = 2;
        /**
         * 未知
         */
        int TYPE_UNKNOWN = 3;
    }

    interface PlayLive {
        /**
         * 从MAG取流标签
         */
        int MAG = 2;
        /**
         * 主码流标签
         */
        int MAIN_STREAM = 0;
        /**
         * 子码流标签
         */
        int SUB_STREAM = 1;

        /**
         * 播放成功
         */
        int PLAY_LIVE_SUCCESS = 200;
        /**
         * 停止播放成功
         */
        int PLAY_LIVE_STOP_SUCCESS = 201;
        /**
         * surfaceview 为空
         */
        int PLAY_SURFACEVIEW_NULL = 202;
        /**
         * camerainfo中的id为空
         */
        int PLAY_CAMERA_INFO_ID_NULL = 203;
        /**
         * 获取设备详细信息失败
         */
        int PLAY_DEVICE_DETAIL_FAILED = 204;

        /**
         * 启动播放失败
         **/
        int PLAY_LIVE_FAILED = 205;
        /**
         * RTSP链接失败
         */
        int PLAY_LIVE_RTSP_FAIL = 206;

        /**
         * 取流成功
         */
        int PLAY_LIVE_RTSP_SUCCESS = 207;

        /**
         * 发送指令成功
         */
        int SEND_CTRL_CMD_SUCCESS = 208;

        /**
         * 发送指令失败
         */
        int SEND_CTRL_CMD_FAILED = 209;

        /**
         * 停止控制成功
         */
        int STOP_CONTROL_SUCCESS = 210;

        /**
         * 停止控制失败
         */
        int STOP_CONTROL_FAILED = 211;


        /**
         * 抓拍失败
         */
        int CAPTURE_FAILED = 212;

        /**
         * 抓拍成功
         */
        int CAPTURE_SUCCESS = 213;

        /**
         * 录像失败
         */
        int RECORD_FAILED = 214;

        /**
         * 开始录像
         */

        int RECORD_START = 216;

        /**
         * 录像成功
         */
        int RECORD_SUCCESS = 215;

        /**
         *  开启声音失败
         */
        int AUDIO_FAILED = 217;

        /**
         *  开启声音成功
         */
        int AUDIO_SUCCESS = 218;

        /**
         *  关闭声音成功
         */
        int AUDIO_CLOSE_SUCCESS = 219;

    }

    interface Control {
        int LEFT_TOP = 11;
        int TOP = 1;
        int RIGHT_TOP = 12;
        int LEFT = 3;
        int RIGHT = 4;
        int LEFT_BOTTOM = 13;
        int BOTTOM = 2;
        int RIGHT_BOTTOM = 14;

        int FAR = 10;
        int NEAR = 9;

        int ZOOM_IN = 7;
        int ZOOM_OUT = 8;
    }

    interface PlayStatus {
        // 定义当前视频的播放状态
        /**
         * 初始化阶段
         */
        int LIVE_INIT = 0;
        /**
         * 取流阶段
         */
        int LIVE_STREAM = 1;
        /**
         * 播放阶段
         */
        int LIVE_PLAY = 2;
        /**
         * 释放资源阶段
         */
        int LIVE_RELEASE = 3;
    }
}
