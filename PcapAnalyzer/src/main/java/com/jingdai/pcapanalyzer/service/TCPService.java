package com.jingdai.pcapanalyzer.service;

import com.jingdai.pcapanalyzer.entity.format.TCPHeader;
import com.jingdai.pcapanalyzer.utils.DataUtils;

import java.util.Arrays;

public class TCPService {

    public TCPHeader parseTCPHeader(byte[] tcpHeaderBuffer) {
        // headerLen
        TCPHeader tcpHeader = new TCPHeader();
        tcpHeader.setHeaderLen(tcpHeaderBuffer.length);

        // sport and dport
        byte[] srcPortBuffer = Arrays.copyOfRange(tcpHeaderBuffer, 0, 2);
        byte[] dstPortBuffer = Arrays.copyOfRange(tcpHeaderBuffer, 2, 4);
        int srcPort = DataUtils.byteArray2Int(srcPortBuffer, 2);
        int dstPort = DataUtils.byteArray2Int(dstPortBuffer, 2);
        tcpHeader.setSrcPort(srcPort);
        tcpHeader.setDstPort(dstPort);
        return tcpHeader;
    }
}
