package com.yq.base.common.networke;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

/**
 * 网络工具类
 */
@SuppressLint("DefaultLocale")
public class NetWorkUtils {
	
	public static final int NET_TYPE_INVALID 	= -1;  			//无效网络
	public static final int NET_TYPE_CMWAP	 	= 0;			//CMWAP(中国移动APN)
	public static final int NET_TYPE_CMNET		= 1;			//CMNET(中国移动APN)
	public static final int NET_TYPE_UNIWAP		= 2;			//UNIWAP(中国联通APN)
	public static final int NET_TYPE_UNINET		= 3;			//UNINET(中国联通APN)
	public static final int NET_TYPE_CTWAP		= 4;			//CTWAP(中国电信APN)
	public static final int NET_TYPE_CTNET      = 5;			//CTNET(中国电信APN)
	public static final int NET_TYPE_WIFI		= 6;			//wifi
	public static final int NET_TYPE_3G_CMWAP   = 7;
	public static final int NET_TYPE_3G_CMNET   = 8;
	public static final int NET_TYPE_4G_CMWAP   = 9;
	public static final int NET_TYPE_4G_CMNET   = 10;
	
	public static final int SIM_MOBILE 			= 0; 			//移动卡
	public static final int SIM_UNICOM 			= 1; 			//联通卡
	public static final int SIM_TELECOM 		= 2; 			//电信卡
	public static final int SIM_INVALID 		= -1; 			//无效卡
	
	/**
	 * 获取网络类型
	 * @return
	 */
	public static int getNetType(Context context){
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = null;
		try{
			activeNetInfo = connectivityManager.getActiveNetworkInfo();
		}catch(Exception e){
			e.printStackTrace();
			return NET_TYPE_INVALID; 	//无效网络
		}
		if (activeNetInfo == null){
			return NET_TYPE_INVALID;	//无效网络
		}
		if(activeNetInfo.getState() == NetworkInfo.State.CONNECTED){
			if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				return NET_TYPE_WIFI;
			} else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				String apn = activeNetInfo.getExtraInfo();
				if(TextUtils.isEmpty(apn)) {
					return NET_TYPE_CMNET;
				}
				apn = apn.toLowerCase();
				if (apn.equals("cmwap") ) {
					return NET_TYPE_CMWAP;
				} else if (apn.equals("cmnet")) {
					return NET_TYPE_CMNET;
				} else if(apn.equals("3gwap")) {
					return NET_TYPE_3G_CMWAP;
				} else if(apn.equals("3gnet")) {
					return NET_TYPE_3G_CMNET;
				} else if (apn.equals("uniwap")) {
					return NET_TYPE_UNIWAP;
				} else if (apn.equals("uninet")) {
					return NET_TYPE_UNINET;
				} else if (apn.equals("ctwap")) { //4.0上电信3g卡ctwap不用代理也能连接
					return NET_TYPE_CTWAP;
				} else if (apn.equals("ctnet") || apn.equals("#777")) {
					return NET_TYPE_CTNET;
				} else {
					return NET_TYPE_CMNET;
				}
			} else {
				return NET_TYPE_CMNET;
			}
		}else{
			NetworkInfo[] netInfos = connectivityManager.getAllNetworkInfo();
            if (netInfos == null) {
            	return NET_TYPE_INVALID;
            }
            int len = netInfos.length;
			for (int i = 0; i < len; i++) {
                if (netInfos[i].getState() == NetworkInfo.State.CONNECTED &&
                		ConnectivityManager.TYPE_MOBILE==netInfos[i].getType()) {
                	String apn = activeNetInfo.getExtraInfo();
    				if(TextUtils.isEmpty(apn)) {
    					return NET_TYPE_CMNET;
    				}
    				apn = apn.toLowerCase();
    				if (apn.equals("cmwap") ) {
    					return NET_TYPE_CMWAP;
    				} else if (apn.equals("cmnet")) {
    					return NET_TYPE_CMNET;
    				} else if(apn.equals("3gwap")) {
    					return NET_TYPE_3G_CMWAP;
    				} else if(apn.equals("3gnet")) {
    					return NET_TYPE_3G_CMNET;
    				} else if (apn.equals("uniwap")) {
    					return NET_TYPE_UNIWAP;
    				} else if (apn.equals("uninet")) {
    					return NET_TYPE_UNINET;
    				} else if (apn.equals("ctwap")) { //4.0上电信3g卡ctwap不用代理也能连接
    					return NET_TYPE_CTWAP;
    				} else if (apn.equals("ctnet") || apn.equals("#777")) {
    					return NET_TYPE_CTNET;
    				} else {
    					return NET_TYPE_CMNET;
    				}
                }
            }
			return NET_TYPE_INVALID;
		}
	}

	/**
	 * 是否连接网络
	 * @return
	 */
	public static boolean isConnectNet(Context context){
		
		return getNetType(context)== NET_TYPE_INVALID?false:true;
	}
	
	/**
	 * 获取sim卡类型
	 * @return
	 */
	public static int getSIMType(Context context){
		int simType = SIM_INVALID;
		TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		if(tm == null) return simType;
		if (tm.getSimState() == TelephonyManager.SIM_STATE_READY) {
			String netCode = tm.getSimOperator();
			if(!TextUtils.isEmpty(netCode)){
				if (netCode.length() > 0) {
					if (netCode.equals("46000") || netCode.equals("46002") || netCode.equals("46007")) {
						simType = SIM_MOBILE; 	//中国移动
					} else if (netCode.equals("46001")) {
						simType = SIM_UNICOM; 	//中国联通
					} else if (netCode.equals("46003") || netCode.equals("46005")) {
						simType = SIM_TELECOM; 	//中国电信
					} else {
						simType = SIM_INVALID; 
					}
				}
			}else{
				simType = SIM_INVALID; 
			}
		}
		return simType;
	}
	
	/**
	 * 获取本机IP地址
	 * @return
	 * @throws SocketException
	 */
	public static String getLocalIPAddress(Context context) throws SocketException {
		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		if(wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
			int ip = wifiManager.getConnectionInfo().getIpAddress();
			if (ip != 0) {
				InetAddress address = intToInet(ip);
				return null!=address?address.getHostAddress():null;
			}
		}
		return "";
	}
	private static InetAddress intToInet(int value) {
		byte[] bytes = new byte[4];
		for(int i = 0; i<4; i++) {
			bytes[i] = byteOfInt(value, i);
		}
		try {
			return InetAddress.getByAddress(bytes);
		} catch (UnknownHostException e) {
			// This only happens if the byte array has a bad length
			return null;
		}
	}
	private static byte byteOfInt(int value, int which) {
		int shift = which * 8;
		return (byte)(value >> shift); 
	}
	
	/**
	 * 判断是否是飞行模式
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean getAirplaneMode(Context context) {
		int isAirplaneMode = Settings.System.getInt(
				context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON,
				0);
		return (isAirplaneMode == 1) ? true : false;
	}
    public static String obtainIP(){
		String IP="";
		IP=getNetIp("http://www.cmyip.com/");
		if (TextUtils.isEmpty(IP)){
			IP=getNetIp("http://city.ip138.com/ip2city.asp");
		}
		return IP;
	}

	/**
	 * 获取当前的外网 IP
	 * @param ipaddr
	 * @return
	 */
	public static String getNetIp(String ipaddr){
		URL infoUrl = null;
		InputStream inStream = null;
		try {
			infoUrl = new URL(ipaddr);
			URLConnection connection = infoUrl.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection)connection;
			int responseCode = httpConnection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK)
			{
				inStream = httpConnection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"utf-8"));
				StringBuilder strber = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null)
					strber.append(line + "\n");
				inStream.close();
				return strber.toString();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}
}
