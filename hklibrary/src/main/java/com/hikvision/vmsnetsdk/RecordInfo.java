/*    */ package com.hikvision.vmsnetsdk;
/*    */ 
/*    */

import java.util.ArrayList;
import java.util.List;

/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecordInfo
/*    */ {
/*    */   public static final int RECORD_TYPE_PLAN = 1;
/*    */   public static final int RECORD_TYPE_MOVE = 2;
/*    */   public static final int RECORD_TYPE_MANU = 16;
/*    */   public static final int RECORD_TYPE_ALARM = 4;
/*    */   public static final int RECORD_POS_IPSAN = 0;
/*    */   public static final int RECORD_POS_DEVICE = 1;
/*    */   public static final int RECORD_POS_PCNVR = 2;
/*    */   public static final int RECORD_POS_ENVR = 3;
/*    */   public static final int RECORD_POS_CISCO = 4;
/*    */   public int queryType;
/* 37 */   public boolean isRecvAll = true;
/*    */   
/*    */   public int segmentCount;
/*    */   
/* 41 */   public String segmentListPlayUrl = null;
/*    */   
/* 43 */   public List<RecordSegment> recSegmentList = new ArrayList();
/*    */   
/*    */ 
/*    */ 
/*    */   public String dayLightStart;
/*    */   
/*    */ 
/*    */   public String dayLightEnd;
/*    */   
/*    */ 
/*    */   public long dayLightMinute;
/*    */   
/*    */ 
/*    */ 
/*    */   public void setRecordInfo(RecordInfo recordInfo)
/*    */   {
/* 59 */     if (recordInfo == null) {
/* 60 */       return;
/*    */     }
/*    */     
/* 63 */     this.queryType = recordInfo.queryType;
/* 64 */     this.isRecvAll = recordInfo.isRecvAll;
/* 65 */     this.segmentCount = recordInfo.segmentCount;
/* 66 */     this.segmentListPlayUrl = recordInfo.segmentListPlayUrl;
/* 67 */     this.dayLightStart = recordInfo.dayLightStart;
/* 68 */     this.dayLightEnd = recordInfo.dayLightEnd;
/* 69 */     this.dayLightMinute = recordInfo.dayLightMinute;
/* 70 */     for (RecordSegment recs : recordInfo.recSegmentList) {
/* 71 */       this.recSegmentList.add(recs);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\vmsnetsdk.jar!\com\hikvision\vmsnetsdk\RecordInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */