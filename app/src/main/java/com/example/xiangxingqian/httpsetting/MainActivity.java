package com.example.xiangxingqian.httpsetting;

import android.net.ProxyInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiangxingqian.httpsetting.wifi.AccessPoint;
import com.example.xiangxingqian.httpsetting.wifi.WifiConfigurationHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 设置wifi代理
 */
public class MainActivity extends AppCompatActivity {

    private static final String SSID = "Techwork";
    private static final String PASSWORD = "20509288";
    private static final String ENCRYPTIONTYPE = "wpa";

    private TextView tvConnect;
    private EditText etInputIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        tvConnect = (TextView) findViewById(R.id.tv_connect);
        etInputIp = (EditText) findViewById(R.id.et_input_ip);

        tvConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkValidIp(etInputIp.getEditableText().toString())) {
                    Toast.makeText(MainActivity.this, "ip无效", Toast.LENGTH_SHORT).show();
                    return;
                }
                String ip = etInputIp.getEditableText().toString();

                connectWifi("192.168." + ip, 8888);
            }
        });
    }

    private boolean checkValidIp(String ip) {
        if (ip == null || ip == "") {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 连接wifi
     */
    private void connectWifi(String ip, int port) {

        //设置wifi名字和密码
        AccessPoint accessPoint = new AccessPoint();
        accessPoint.setSsid(SSID);
        accessPoint.setPassword(PASSWORD);
        accessPoint.setEncryptionType(ENCRYPTIONTYPE);

        //设置本机的ip
        WifiConfiguration configuration = WifiConfigurationHelper.createConfiguration(accessPoint);
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        ProxyInfo proxyInfo = ProxyInfo.buildDirectProxy(ip, port);

        //调用setProxy方法
        try {
            Class proxySettings = Class.forName("android.net.IpConfiguration$ProxySettings");
            Method setProxy = WifiConfiguration.class.getMethod("setProxy", proxySettings, ProxyInfo.class);
            setProxy.invoke(configuration, proxySettings.getEnumConstants()[1], proxyInfo);
            Toast.makeText(MainActivity.this, "Proxy is set successfully!", Toast.LENGTH_SHORT).show();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int networkId = wifiManager.addNetwork(configuration);
        wifiManager.enableNetwork(networkId, true);
    }

}
