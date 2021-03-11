package com.jingdai.pcapanalyzer.service;

import com.jingdai.pcapanalyzer.entity.format.IPHeader;
import com.jingdai.pcapanalyzer.utils.DataUtils;

import java.util.Arrays;

public class IPService {

    public IPHeader parseIPHeader(byte[] ipHeaderBuffer) {
        IPHeader ipHeader = new IPHeader();
        int headerLen = ipHeaderBuffer.length;
        ipHeader.setHeaderLen(headerLen);
        // 首部和数据长度和
        byte[] totalLenBuffer = Arrays.copyOfRange(ipHeaderBuffer, 2, 4);
        int totalLen = DataUtils.byteArray2Int(totalLenBuffer, 2);
        ipHeader.setTotalLen(totalLen);

        // upper protocol
        // 6 represents tcp
        int protocol = DataUtils.byteToInt(ipHeaderBuffer[9]);
        ipHeader.setProtocol(protocol);

        // parse sip and dip
        byte[] srcIPBuffer = Arrays.copyOfRange(ipHeaderBuffer, 12, 16);
        byte[] dstIPBuffer = Arrays.copyOfRange(ipHeaderBuffer, 16, 20);
        int srcIP = DataUtils.byteArray2Int(srcIPBuffer, 4);
        int dstIP = DataUtils.byteArray2Int(dstIPBuffer, 4);
        ipHeader.setSrcIP(srcIP);
        ipHeader.setDstIP(dstIP);
        return ipHeader;
    }

}
