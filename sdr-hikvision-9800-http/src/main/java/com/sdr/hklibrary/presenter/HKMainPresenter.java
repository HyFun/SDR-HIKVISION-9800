package com.sdr.hklibrary.presenter;

import com.hikvision.vmsnetsdk.ServInfo;
import com.hikvision.vmsnetsdk.VMSNetSDK;
import com.sdr.hklibrary.base.HKBasePresenter;
import com.sdr.hklibrary.contract.HKMainContract;
import com.sdr.hklibrary.support.HKResourceHelper;
import com.sdr.lib.rx.RxUtils;
import com.sdr.lib.ui.tree.TreeNode;
import com.sdr.lib.util.HttpUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description:
 */

public class HKMainPresenter extends HKBasePresenter<HKMainContract.View> implements HKMainContract.Presenter {
    public HKMainPresenter(HKMainContract.View view) {
        super(view);
    }

    /**
     * 登录  并获取摄像头列表
     *
     * @param url
     * @param userName
     * @param passWord
     * @param macAddr
     * @param servInfo
     */
    @Override
    public void init(final String url, final String userName, final String passWord, final String macAddr, final ServInfo servInfo) {
        Disposable disposable = Observable.just(0)
                .flatMap(new Function<Integer, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Integer integer) throws Exception {
                        // 登录
                        boolean ret = VMSNetSDK.getInstance().login(url, userName, passWord, macAddr, servInfo, HttpUtil.clearDomainAddress(url));
                        if (ret) {
                            return RxUtils.createData(ret);
                        } else {
                            return Observable.error(new Exception(VMSNetSDK.getInstance().getLastErrorCode() + "，登录失败"));
                        }
                    }
                })
                .flatMap(new Function<Boolean, ObservableSource<List<TreeNode>>>() {
                    @Override
                    public ObservableSource<List<TreeNode>> apply(Boolean aBoolean) throws Exception {
                        HKResourceHelper resourceHelper = new HKResourceHelper(url, servInfo);
                        resourceHelper.start();
                        return RxUtils.createData(resourceHelper.getTreeNodeList());
                    }
                })
                .compose(RxUtils.io_main())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        view.initSuccess((List<TreeNode>) object);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.initFailed(throwable.getMessage());
                    }
                });

        addSubscription(disposable);
    }

}
