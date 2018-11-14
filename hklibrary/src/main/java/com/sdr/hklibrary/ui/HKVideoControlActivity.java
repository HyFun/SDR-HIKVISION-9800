package com.sdr.hklibrary.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.sdr.hklibrary.R;
import com.sdr.hklibrary.base.HKBaseActivity;
import com.sdr.hklibrary.constant.HKConstants;
import com.sdr.hklibrary.contract.HKPlayContract;
import com.sdr.hklibrary.data.HKItemControl;
import com.sdr.lib.rx.RxUtils;
import com.sdr.lib.widget.SquareFramLayout;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class HKVideoControlActivity extends HKBaseActivity implements HKPlayContract.View {

    public static final int RQ_OPEN_HKVIDEO_CONTROL_CODE = 213;
    private static final String CAMERAID = "CAMERAID";


    SquareFramLayout flContainer;
    SurfaceView mSurfaceView;

    /**
     * 十二个 控制按钮
     */
    ImageView ivLeftTop;
    ImageView ivTop;
    ImageView ivRightTop;
    ImageView ivLeft;
    ImageView ivRight;
    ImageView ivLeftBottom;
    ImageView ivBottom;
    ImageView ivRightBottom;
    ImageView ivFar;
    ImageView ivNear;
    ImageView ivZoomIn;
    ImageView ivZoomOut;
    ImageView ivMiddle;


    private String cameraId;
    private HKItemControl mHKItemControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hkvideo_control);
        initIntent();
        initToolbar();
        initView();
        initListener();
    }

    private void initIntent() {
        Intent intent = getIntent();
        cameraId = intent.getStringExtra(CAMERAID);
        if (cameraId == null) finish();
    }

    private void initToolbar() {
        setTitle("预览视频");
        setDisplayHomeAsUpEnabled();

        flContainer = findViewById(R.id.hk_video_control_sfl_container);
        mSurfaceView = findViewById(R.id.hk_video_control_surfaceview);
        ivLeftTop = findViewById(R.id.hk_video_control_iv_left_top);
        ivTop = findViewById(R.id.hk_video_control_iv_top);
        ivRightTop = findViewById(R.id.hk_video_control_iv_right_top);
        ivLeft = findViewById(R.id.hk_video_control_iv_left);
        ivRight = findViewById(R.id.hk_video_control_iv_right);
        ivLeftBottom = findViewById(R.id.hk_video_control_iv_left_bottom);
        ivBottom = findViewById(R.id.hk_video_control_iv_bottom);
        ivRightBottom = findViewById(R.id.hk_video_control_iv_right_bottom);
        ivFar = findViewById(R.id.hk_video_control_iv_far);
        ivNear = findViewById(R.id.hk_video_control_iv_near);
        ivZoomIn = findViewById(R.id.hk_video_control_iv_zoom_in);
        ivZoomOut = findViewById(R.id.hk_video_control_iv_zoom_out);

        ivMiddle = findViewById(R.id.hk_video_control_iv_middle);

    }

    private void initView() {
        mHKItemControl = new HKItemControl(0, this);
        mHKItemControl.startPlay(cameraId, mSurfaceView);
    }

    private void initListener() {
        ivLeftTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.LEFT_TOP);
            }
        });

        ivTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.TOP);
            }
        });

        ivRightTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.RIGHT_TOP);
            }
        });

        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.LEFT);
            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.RIGHT);
            }
        });

        ivLeftBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.LEFT_BOTTOM);
            }
        });

        ivBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.BOTTOM);
            }
        });

        ivRightBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.RIGHT_BOTTOM);
            }
        });

        ivFar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.FAR);
            }
        });

        ivNear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.NEAR);
            }
        });

        ivZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.ZOOM_IN);
            }
        });

        ivZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCtrlCmd(HKConstants.Control.ZOOM_OUT);
            }
        });

        ivMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHKItemControl.stopControl();
            }
        });
    }

    /**
     * 发送指令
     *
     * @param cmd
     */
    private void sendCtrlCmd(int cmd) {
        if (mHKItemControl.getCurrentStatus() == HKConstants.PlayStatus.LIVE_INIT) {
            showErrorToast("视频没有正在播放，无法控制");
            return;
        }
        mHKItemControl.sendCtrlCmd(cmd);
    }

    @Override
    protected void setNavigationOnClickListener() {
        new MaterialDialog.Builder(getContext())
                .title("提示")
                .content("退出预览视频？")
                .positiveText("退出")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Observable.just(0)
                                .flatMap(new Function<Integer, ObservableSource<Boolean>>() {
                                    @Override
                                    public ObservableSource<Boolean> apply(Integer integer) throws Exception {
                                        mHKItemControl.stopPlaySyn();
                                        return observer -> {
                                            observer.onNext(true);
                                            observer.onComplete();
                                        };
                                    }
                                }).compose(RxUtils.io_main())
                                .subscribe(ret -> {
                                    finish();
                                });
                    }
                })
                .show();
    }

    @Override
    public void onBackPressed() {
        setNavigationOnClickListener();
    }

    @Override
    public void onPlayMsg(int position, int code, String msg) {
        if (code == HKConstants.PlayLive.PLAY_LIVE_RTSP_SUCCESS) {
            // 取流成功
        } else if (code == HKConstants.PlayLive.PLAY_LIVE_STOP_SUCCESS) {
            // 停止成功
        } else if (code == HKConstants.PlayLive.PLAY_LIVE_FAILED || code == HKConstants.PlayLive.PLAY_LIVE_RTSP_FAIL) {
            // 播放失败
            showErrorMsg(msg);
        } else if (code == HKConstants.PlayLive.SEND_CTRL_CMD_SUCCESS) {
            // 指令执行成功
            showErrorToast(msg);
        } else if (code == HKConstants.PlayLive.SEND_CTRL_CMD_FAILED) {
            // 指令执行失败
            showErrorToast(msg);
        }
    }


    /**
     * 开启此activity
     *
     * @param activity
     * @param cameraId
     */
    public static void startHKVideoControlActivity(Activity activity, String cameraId) {
        Intent intent = new Intent(activity, HKVideoControlActivity.class);
        intent.putExtra(CAMERAID, cameraId);
        activity.startActivityForResult(intent, RQ_OPEN_HKVIDEO_CONTROL_CODE);
    }
}
