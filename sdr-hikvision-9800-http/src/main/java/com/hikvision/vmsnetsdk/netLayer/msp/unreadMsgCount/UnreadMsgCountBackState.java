//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.netLayer.msp.unreadMsgCount;

import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;

public class UnreadMsgCountBackState extends MspBackState {
    public static final int CODE_NO_DATA = 201;
    public static final int CODE_PARAM_ERROR = 205;
    public static final int CODE_SESSION_ERROR = 206;
    public static final int CODE_NO_SUPPORT = 207;
    public static final int CODE_EXCEPTION = 230;
    public static final int CODE_ERROR = 231;

    public UnreadMsgCountBackState() {
    }

    public boolean isSuccess() {
        return this.code == 200;
    }
}
