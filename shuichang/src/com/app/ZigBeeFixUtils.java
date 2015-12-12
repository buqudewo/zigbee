package com.app;

import java.util.ArrayList;

import com.lr.javaBean.DataALL;
/*
 * ZigBee协议配置偏移量offset
 */
public class ZigBeeFixUtils {
	public static byte getOffsetByDeiveId(MyApplication myApplication,String deviceId){
		ArrayList<DataALL> dataAlls=myApplication.getAlls();
		byte offset=-1;
		for (DataALL dataALL : dataAlls) {
			if(dataALL.getDeviceID().equals(deviceId)){
				offset=dataALL.getOffset();
			    break;
			}
		}
		System.out.println("--- "+deviceId+" offset : "+(int)offset);
		return offset;
	}
	public static  void setOffsetByDeiveId(MyApplication myApplication,String deviceId,byte offset){
		ArrayList<DataALL> dataAlls=myApplication.getAlls();
		for (int i=0;i<dataAlls.size();i++) {
			if(dataAlls.get(i).getDeviceID().equals(deviceId)){
				dataAlls.get(i).setOffset(offset);
			    break;
			}
		}
		System.out.println("init "+deviceId+" offset : "+(int)offset);
	}
}
