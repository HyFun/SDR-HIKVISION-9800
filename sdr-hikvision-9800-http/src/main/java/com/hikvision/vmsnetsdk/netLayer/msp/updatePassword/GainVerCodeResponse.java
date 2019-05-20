//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hikvision.vmsnetsdk.netLayer.msp.updatePassword;

import com.hikvision.vmsnetsdk.CNetSDKLog;
import com.hikvision.vmsnetsdk.netLayer.msp.MspBackState;
import com.hikvision.vmsnetsdk.netLayer.msp.MspResponse;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class GainVerCodeResponse extends MspResponse {
    private static final String TAG = "GainVerCodeResponse";
    UpdatePasswordBackState state;

    public GainVerCodeResponse(String responseData) {
        super(responseData);
    }

    public MspBackState getMspBackState() {
        return this.state;
    }

    protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception {
        this.state = new UpdatePasswordBackState();
        return this.doParseCycle(xmlPullParser);
    }

    protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName) throws NumberFormatException, XmlPullParserException, IOException, Exception {
        if(xmlPullParser != null && nodeName != null) {
            String description;
            if("Status".equals(nodeName)) {
                description = xmlPullParser.nextText();
                this.state.setCode(Integer.parseInt(description.trim()));
                CNetSDKLog.d("GainVerCodeResponse", "parseResponseData,status：" + description);
            } else if("Description".equals(nodeName)) {
                description = xmlPullParser.nextText();
                this.state.setDescription(description);
                CNetSDKLog.d("GainVerCodeResponse", "parseResponseData,description：" + description);
            }

        } else {
            CNetSDKLog.e("GainVerCodeResponse", "handleXMLStartTag,param error");
            throw new Exception("handleXMLStartTag,param error");
        }
    }
}
