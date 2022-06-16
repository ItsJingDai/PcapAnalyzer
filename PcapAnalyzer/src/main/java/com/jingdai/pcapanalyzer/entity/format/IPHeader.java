package com.jingdai.pcapanalyzer.entity.format;

import com.jingdai.pcapanalyzer.utils.IPUtils;

/**
 * IP Header
 */
public class IPHeader {

    public static final int PROTOCOL_TCP = 6;
    public static final int PROTOCOL_UDP = 17;

    /**
     * head
     */
    private int headerLen;

    /**
     * total len(2 Byte)
     */
    private int totalLen;

    /**
     * protocol 1 Byte
     */
    private int protocol;

    /**
     * source IP(4 Byte)
     */
    private int srcIP;

    /**
     * destination IP(4 Byte)
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

