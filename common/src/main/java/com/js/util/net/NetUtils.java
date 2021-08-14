package com.js.util.net;

import org.apache.commons.lang3.StringUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author 渡劫 dujie
 * @date 2020/7/20 19:36
 **/
public class NetUtils {

    /**
     * 获取本地真正的IP地址，即获得有线或者 无线WiFi 地址。
     * 过滤虚拟机、蓝牙等地址
     *
     * @return IPv4
     */
    public static String getRealIP() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                // 去除回环接口，子接口，未运行和接口
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }

                if (!netInterface.getDisplayName().contains("Intel")
                        && !netInterface.getDisplayName().contains("Realtek")
                        && !netInterface.getDisplayName().contains("Ethernet")) {
                    continue;
                }

                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip != null) {
                        // ipv4
                        if (ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
                break;
            }
        } catch (SocketException e) {
            //捕获异常
        }
        return null;
    }

    /**
     * @Description: 获取本机地址
     * @Return: java.lang.String
     * @Author: 渡劫 dujie
     * @Date: 2021/1/2 11:16 PM
     **/
    public static String getLocalIp() {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface nif = netInterfaces.nextElement();
                Enumeration<InetAddress> InetAddress = nif.getInetAddresses();
                while (InetAddress.hasMoreElements()) {
                    String ip = InetAddress.nextElement().getHostAddress();
                    if (ip.startsWith("192.168")) {
                        return ip;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    /**
     * @param ip
     * @Description: 获取ip的末尾内容
     * @Return: java.lang.String
     * @Author: 渡劫 dujie
     * @Date: 2021/1/2 11:31 PM
     **/
    public static String getLastIp(String ip) {
        if (StringUtils.isNotBlank(ip)) {
            String[] ipList = StringUtils.split(ip, ".");
            return ipList[ipList.length - 1];
        }
        return "1";
    }

    /**
     * @param ip
     * @Description: 获取ip的第三组数据
     * @Return: java.lang.String
     * @Author: 渡劫 dujie
     * @Date: 2021/1/4 12:49 PM
     **/
    public static String getThreeIp(String ip) {
        if (StringUtils.isNotBlank(ip)) {
            String[] ipList = StringUtils.split(ip, ".");
            return ipList[ipList.length - 2];
        }
        return "1";
    }
}
