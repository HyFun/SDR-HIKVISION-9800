//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.netLayer.msp.unreadMsgCount;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class UnreadMsgCountResponse extends MspResponse {
    private static final String TAG = "UnreadMsgCountResponse";
    private UnreadMsgCountBackState unreadMsgCountBackState;
    private int totalCount;
    private int smCount;
    private int pmCount;
    private int tmCount;
    private int amCount;

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getSmCount() {
        return this.smCount;
    }

    public int getPmCount() {
        return this.pmCount;
    }

    public int getTmCount() {
        return this.tmCount;
    }

    public int getAmCount() {
        return this.amCount;
    }

    public UnreadMsgCountResponse(String responseData) {
        super(responseData);
    }

    public String getResponseData() {
        return super.getResponseData();
    }

    protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName) throws NumberFormatException, XmlPullParserException, IOException, Exception {
        if(xmlPullParser != null && nodeName != null) {
            String amCount;
            if("Status".equals(nodeName)) {
                amCount = xmlPullParser.nextText();
                this.unreadMsgCountBackState.setCode(Integer.parseInt(amCount.trim()));
                CNetSDKLog.d("UnreadMsgCountResponse", "handleXMLStartTag,status：" + amCount);
            } else if("Description".equals(nodeName)) {
                amCount = xmlPullParser.nextText();
                this.unreadMsgCountBackState.setDescription(amCount);
                CNetSDKLog.d("UnreadMsgCountResponse", "handleXMLStartTag,description：" + amCount);
            } else if("TotalCount".equals(nodeName)) {
                amCount = xmlPullParser.nextText();
                CNetSDKLog.d("UnreadMsgCountResponse", "handleXMLStartTag,totalCount：" + amCount);
                this.totalCount = Integer.parseInt(amCount);
            } else if("SMCount".equals(nodeName)) {
                amCount = xmlPullParser.nextText();
                CNetSDKLog.d("UnreadMsgCountResponse", "handleXMLStartTag,smCount：" + amCount);
                this.smCount = Integer.parseInt(amCount);
            } else if("PMCount".equals(nodeName)) {
                amCount = xmlPullParser.nextText();
                CNetSDKLog.d("UnreadMsgCountResponse", "handleXMLStartTag,pmCount：" + amCount);
                this.pmCount = Integer.parseInt(amCount);
            } else if("TMCount".equals(nodeName)) {
                amCount = xmlPullParser.nextText();
                CNetSDKLog.d("UnreadMsgCountResponse", "handleXMLStartTag,tmCount：" + amCount);
                this.tmCount = Integer.parseInt(amCount);
            } else if("AMCount".equals(nodeName)) {
                amCount = xmlPullParser.nextText();
                CNetSDKLog.d("UnreadMsgCountResponse", "handleXMLStartTag,amCount：" + amCount);
                this.amCount = Integer.parseInt(amCount);
            }

        } else {
            CNetSDKLog.e("UnreadMsgCountResponse", "handleXMLStartTag,param error");
            throw new Exception("handleXMLStartTag,param error");
        }
    }

    public MspBackState getMspBackState() {
        return this.unreadMsgCountBackState;
    }

    protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception {
        this.unreadMsgCountBackState = new UnreadMsgCountBackState();
        return this.doParseCycle(xmlPullParser);
    }
}
