/*    */ package com.hikvision.vmsnetsdk;
/*    */ 
/*    */

import com.hikvision.vmsnetsdk.netLayer.msp.msg.Msg;
import com.hikvision.vmsnetsdk.netLayer.msp.msg.detail.MsgDetail;

/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SDKBulletinDetail
/*    */   extends SDKBulletin
/*    */ {
/*    */   private String content;
/*    */   
/*    */   public String getContent()
/*    */   {
/* 17 */     return this.content;
/*    */   }
/*    */   
/*    */   public void setContent(String content) {
/* 21 */     this.content = content;
/*    */   }
/*    */   
/*    */   public void setSDKBulletinDetail(MsgDetail sdkBulletinDetail) {
/* 25 */     if (sdkBulletinDetail == null) {
/* 26 */       return;
/*    */     }
/*    */     
/* 29 */     setSDKBulletinDetailBaseInfo(sdkBulletinDetail);
/*    */     
/* 31 */     setContent(sdkBulletinDetail.getContent());
/*    */   }
/*    */   
/*    */   protected void setSDKBulletinDetailBaseInfo(Msg msg)
/*    */   {
/* 36 */     if (msg == null) {
/* 37 */       return;
/*    */     }
/* 39 */     setTitle(msg.getTitle());
/* 40 */     setType(msg.getType());
/* 41 */     setChecked(msg.isChecked());
/* 42 */     setCreateTime(msg.getCreateTime());
/* 43 */     setTypeDescribe(msg.getTypeDescribe());
/* 44 */     setId(msg.getId());
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\SDKBulletinDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */