package com.app;

import java.util.ArrayList;

import com.lr.javaBean.DataALL;
/*
 * ZigBee协议配置偏移量offset
 */
import com.lr.javaBean.Utility;
public class ZigBeeFixUtils {
	public static byte getOffsetByDeiveId(MyApplication myApplication,String deviceId){
		ArrayList<DataALL> dataAlls=myApplication.getAlls();
		byte offset=-1;
		for (DataALL dataALL : dataAlls) {
			if(dataALL.getDeviceID().equals(deviceId)){
				offset=dataALL.getOffset();
				System.out.println("取出--- "+deviceId+" offset : "+offset);
			    break;
			}
		}
		
		return offset;
	}
	public static  void setOffsetByDeiveId(MyApplication myApplication,String deviceId,byte offset){
		String DEVICE_ID=getRealDeviceId(deviceId,offset);
		
		ArrayList<DataALL> dataAlls=myApplication.getAlls();
		for (int i=0;i<dataAlls.size();i++) {
			if(dataAlls.get(i).getDeviceID().equals(DEVICE_ID)){
				dataAlls.get(i).setOffset(offset);
				System.out.println("缓存cache offset "+offset+" deviceId "+deviceId+" real DEVICE_ID "+DEVICE_ID);
			    break;
			}
		}
		
	}
	
	public static String getRealDeviceId(String machineDeviceId,byte offset){
		byte[] bytes=new byte[4];
		System.arraycopy(
				Utility.HexString2Bytes(machineDeviceId.replaceAll("-", "").trim()),
				0, bytes, 0, 4);
		String deviceID = Utility.byteToHex(bytes[0]) + "-"
				+ Utility.byteToHex(bytes[1]) + "-"
				+ Utility.byteToHex(bytes[2]) + "-"
				+ Utility.byteToHex((byte)(bytes[3]+offset));
		System.out.println("获取设备真实  deviceID :"+deviceID +"offset : "+offset+" 主机machineDeviceId "+machineDeviceId);
		return deviceID;
	}
	public static String getMianMachineDeviceId(String deviceId,int offset){
		byte[] bytes=new byte[4];
		System.arraycopy(
				Utility.HexString2Bytes(deviceId.replaceAll("-", "").trim()),
				0, bytes, 0, 4);
		String deviceID = Utility.byteToHex(bytes[0]) + "-"
				+ Utility.byteToHex(bytes[1]) + "-"
				+ Utility.byteToHex(bytes[2]) + "-"
				+ Utility.byteToHex((byte)(bytes[3]-offset));
		System.out.println(" deviceID :"+deviceID +"offset :"+offset);
		return deviceID;
		
	}
}
