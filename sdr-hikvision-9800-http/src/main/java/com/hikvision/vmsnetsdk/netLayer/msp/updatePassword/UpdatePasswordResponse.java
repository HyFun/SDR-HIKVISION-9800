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

public class UpdatePasswordResponse extends MspResponse {
    private static final String TAG = "UpdatePasswordResponse";
    private UpdatePasswordBackState updatePasswordBackState;

    public UpdatePasswordResponse(String responseData) {
        super(responseData);
    }

    public String getResponseData() {
        return super.getResponseData();
    }

    public MspBackState getMspBackState() {
        return this.updatePasswordBackState;
    }

    protected boolean doParse(XmlPullParser xmlPullParser) throws XmlPullParserException, Exception {
        this.updatePasswordBackState = new UpdatePasswordBackState();
        return this.doParseCycle(xmlPullParser);
    }

    protected void handleXMLStartTag(XmlPullParser xmlPullParser, String nodeName) throws NumberFormatException, XmlPullParserException, IOException, Exception {
        if(xmlPullParser != null && nodeName != null) {
            String description;
            if("Status".equals(nodeName)) {
                description = xmlPullParser.nextText();
                this.updatePasswordBackState.setCode(Integer.parseInt(description.trim()));
                CNetSDKLog.d("UpdatePasswordResponse", "parseResponseData,status：" + description);
            } else if("Description".equals(nodeName)) {
                description = xmlPullParser.nextText();
                this.updatePasswordBackState.setDescription(description);
                CNetSDKLog.d("UpdatePasswordResponse", "parseResponseData,description：" + description);
            }

        } else {
            CNetSDKLog.e("UpdatePasswordResponse", "handleXMLStartTag,param error");
            throw new Exception("handleXMLStartTag,param error");
        }
    }
}
