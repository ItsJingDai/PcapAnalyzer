package com.jingdai.pcapanalyzer.entity.format;

import com.jingdai.pcapanalyzer.utils.IPUtils;

/**
 * IP 数据报头
 */
public class IPHeader {

    public static final int PROTOCOL_TCP = 6;
    public static final int PROTOCOL_UDP = 17;

    /**
     * 首部长度
     */
    private int headerLen;

    /**
     * 总长度（2 字节）
     */
    private int totalLen;

    /**
     * 协议类型（1 字节）
     */
    private int protocol;

    /**
     * 源 IP（4 字节）
     */
    private int srcIP;

    /**
     * 目的 IP（4 字节）
     */
    private int dstIP;

    public void setTotalLen(short totalLen) {
        this.totalLen = totalLen;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getSrcIP() {
        return srcIP;
    }

    public void setSrcIP(int srcIP) {
        this.srcIP = srcIP;
    }

    public int getDstIP() {
        return dstIP;
    }

    public void setDstIP(int dstIP) {
        this.dstIP = dstIP;
    }

    public void setTotalLen(int totalLen) {
        this.totalLen = totalLen;
    }

    public int getHeaderLen() {

        return headerLen;
    }

    public void setHeaderLen(int headerLen) {
        this.headerLen = headerLen;
    }

    public IPHeader() {	}

    @Override
    public String toString() {
        return "IPHeader{" +
                "headerLen=" + headerLen +
                ", totalLen=" + totalLen +
                ", protocol=" + protocol +
                ", srcIP=" + IPUtils.int2IPv4(srcIP) +
                ", dstIP=" + IPUtils.int2IPv4(dstIP)+
                '}';
    }
}

