package com.jingdai.pcapanalyzer.utils;

public class IPUtils {

    public static String int2IPv4(int intIp) {
        StringBuilder sb = new StringBuilder();
        sb.append(intIp >>> 24);
        sb.append(".");
        sb.append((intIp >>> 16) & 0xff);
        sb.append(".");
        sb.append((intIp >>> 8) & 0xff);
        sb.append(".");
        sb.append(intIp & 0xff);
        return sb.toString();
    }

}
