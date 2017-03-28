package com.example.xiangxingqian.httpsetting.wifi;

import java.util.ArrayList;

/**
 * 接入点
 * Created by xiangxingqian on 2017/3/28.
 */
public class AccessPoint {
    private String ssid;//wifi名
    private String bssid;
    private String password; //wifi密码
    private float signalStrength;  // 信号强度
    private String encryptionType; //加密类型
    private int networkId; //wifi id
    private ArrayList<AccessPoint> relativeAPs;//其他接入点

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(float signalStrength) {
        this.signalStrength = signalStrength;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public ArrayList<AccessPoint> getRelativeAPs() {
        return relativeAPs;
    }

    public void setRelativeAPs(ArrayList<AccessPoint> relativeAPs) {
        this.relativeAPs = relativeAPs;
    }
}