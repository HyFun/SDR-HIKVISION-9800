package com.sdr.hklibrary.support;

import com.hikvision.vmsnetsdk.CameraInfo;
import com.hikvision.vmsnetsdk.ControlUnitInfo;
import com.hikvision.vmsnetsdk.RegionInfo;
import com.hikvision.vmsnetsdk.ServInfo;
import com.hikvision.vmsnetsdk.VMSNetSDK;
import com.orhanobut.logger.Logger;
import com.sdr.hklibrary.constant.HKConstants;
import com.sdr.lib.ui.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HyFun on 2018/11/13.
 * Email: 775183940@qq.com
 * Description: 获取摄像头树形列表   需要在子线程中调用
 */

public class HKResourceHelper {
    private List<TreeNode> treeNodeList = new ArrayList<>();
    private String url;
    private ServInfo servInfo;

    public HKResourceHelper(String url, ServInfo servInfo) {
        this.url = url;
        this.servInfo = servInfo;
    }

    public List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }

    public void start() {
        // 开始获取数据
        reqResList(HKConstants.Resource.TYPE_UNKNOWN, 0);
    }

    /**
     * 请求获取列表  第一次手动调用的时候pId写0
     * 循环获取列表
     *
     * @param pType
     * @param pId
     */
    private void reqResList(int pType, int pId) {
        switch (pType) {
            case HKConstants.Resource.TYPE_UNKNOWN:
                treeNodeList.clear();
                requestSubResFromCtrlUnit(0);
                break;
            case HKConstants.Resource.TYPE_CTRL_UNIT:
                requestSubResFromCtrlUnit(pId);
                break;
            case HKConstants.Resource.TYPE_REGION:
                requestSubResFromRegion(pId);
                break;
        }
    }

    /**
     * 从控制中心获取控制中心   区域   监控点
     *
     * @param pId
     */
    private void requestSubResFromCtrlUnit(int pId) {
        String servAddr = url;
        ServInfo loginData = servInfo;
        String sessionID = loginData.getSessionID();
        int controlUnitID = pId;// 控制中心id
        int numPerPage = 10000;// 此处取10000，表示每页获取的数量，这个数值可以根据实际情况进行修改
        int curPage = 1;// 当前获取的数据是第几页
        // 1.从控制中心获取控制中心
        List<ControlUnitInfo> ctrlUnitList = new ArrayList<ControlUnitInfo>();
        boolean ret = VMSNetSDK.getInstance().getControlUnitList(servAddr, sessionID, String.valueOf(controlUnitID), numPerPage,
                curPage, ctrlUnitList);
        if (!ret) {
            Logger.t(HKConstants.HK_TAG).d("从控制中心获取控制中心列表失败>>>>>>>>code：" + VMSNetSDK.getInstance().getLastErrorCode() + ">>>>>>des" + VMSNetSDK.getInstance().getLastErrorDesc());
        }
        // 将获取的添加到TreeNode集合中
        for (ControlUnitInfo unit : ctrlUnitList) {
            treeNodeList.add(new TreeNode(unit.getControlUnitID() + "", pId + "", unit.getName(), false, false, unit));
        }
        for (ControlUnitInfo unit : ctrlUnitList) {
            // 遍历获取该中心下的资源
            reqResList(HKConstants.Resource.TYPE_CTRL_UNIT, Integer.parseInt(unit.getControlUnitID()));
        }

        // 2.从控制中心获取区域列表
        List<RegionInfo> regionList = new ArrayList<RegionInfo>();
        ret = VMSNetSDK.getInstance().getRegionListFromCtrlUnit(servAddr, sessionID, String.valueOf(controlUnitID), numPerPage,
                curPage, regionList);
        if (!ret) {
            Logger.t(HKConstants.HK_TAG).d("从控制中心获取组织列表失败>>>>>>>>code：" + VMSNetSDK.getInstance().getLastErrorCode() + ">>>>>>des" + VMSNetSDK.getInstance().getLastErrorDesc());
        }
        // 将获取的添加到TreeNode集合中
        for (RegionInfo region : regionList) {
            treeNodeList.add(new TreeNode(region.getRegionID() + "", pId + "", region.getName(), false, false, region));
        }
        for (RegionInfo region : regionList) {
            // 遍历获取该区域下的资源
            reqResList(HKConstants.Resource.TYPE_REGION, Integer.parseInt(region.getRegionID()));
        }


        // 3.从控制中心获取摄像头列表
        List<CameraInfo> cameraList = new ArrayList<CameraInfo>();
        ret = VMSNetSDK.getInstance().getCameraListFromCtrlUnit(servAddr, sessionID, String.valueOf(controlUnitID), numPerPage,
                curPage, cameraList);
        if (!ret) {
            Logger.t(HKConstants.HK_TAG).d("从控制中心获取摄像头列表失败>>>>>>>>code：" + VMSNetSDK.getInstance().getLastErrorCode() + ">>>>>>des" + VMSNetSDK.getInstance().getLastErrorDesc());
        }
        // 将获取的添加到TreeNode集合中
        for (CameraInfo camera : cameraList) {
            treeNodeList.add(new TreeNode(camera.getId() + "", pId + "", camera.getName(), false, true, camera));
        }
    }

    /**
     * 从区域获取  区域   监控点
     *
     * @param pId
     */
    private void requestSubResFromRegion(int pId) {
        String servAddr = url;
        ServInfo loginData = servInfo;
        int numPerPage = 10000;
        int curPage = 1;
        // 1.从区域获取区域列表
        List<RegionInfo> regionList = new ArrayList<RegionInfo>();
        boolean ret = VMSNetSDK.getInstance().getRegionListFromRegion(servAddr, loginData.getSessionID()
                , String.valueOf(pId), numPerPage, curPage, regionList);
        if (!ret) {
            Logger.t(HKConstants.HK_TAG).d("从组织获取组织列表失败>>>>>>>>code：" + VMSNetSDK.getInstance().getLastErrorCode() + ">>>>>>des" + VMSNetSDK.getInstance().getLastErrorDesc());
        }
        // 将获取的添加到TreeNode集合中
        for (RegionInfo region : regionList) {
            treeNodeList.add(new TreeNode(region.getRegionID() + "", pId + "", region.getName(), false, false, region));
        }
        for (RegionInfo region : regionList) {
            // 遍历获取该区域下的资源
            reqResList(HKConstants.Resource.TYPE_REGION, Integer.parseInt(region.getRegionID()));
        }

        // 2.从区域获取监控点（摄像头）列表
        List<CameraInfo> cameraList = new ArrayList<CameraInfo>();
        ret = VMSNetSDK.getInstance().getCameraListFromRegion(servAddr, loginData.getSessionID(), String.valueOf(pId), numPerPage, curPage,
                cameraList);
        if (!ret) {
            Logger.t(HKConstants.HK_TAG).d("从组织获取摄像头列表失败>>>>>>>>code：" + VMSNetSDK.getInstance().getLastErrorCode() + ">>>>>>des" + VMSNetSDK.getInstance().getLastErrorDesc());
        }
        // 将获取的添加到TreeNode集合中
        for (CameraInfo camera : cameraList) {
            treeNodeList.add(new TreeNode(camera.getId() + "", pId + "", camera.getName(), false, true, camera));
        }
    }


}
