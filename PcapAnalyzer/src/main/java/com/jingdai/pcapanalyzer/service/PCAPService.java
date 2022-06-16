package com.jingdai.pcapanalyzer.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import com.jingdai.pcapanalyzer.entity.format.*;
import com.jingdai.pcapanalyzer.utils.*;

public class PCAPService {

    private FrameService frameService = new FrameService();
    private IPService ipService = new IPService();
    private TCPService tcpService = new TCPService();

    public void parsePcap(File pcapFile) {

        // pcap global header: 24 bytes
        byte[] globalHeaderBuffer = new byte[24];
        // pcap packet header: 16 bytes
        byte[] packetHeaderBuffer = new byte[16];
        byte[] packetDataBuffer;

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(pcapFile);
            if(fis.read(globalHeaderBuffer) != 24) {
                System.out.println("The Pcap file is broken!");
                return;
            }

            // 解析 Global Header
            GlobalHeader globalHeader = parseGlobalHeader(globalHeaderBuffer);
            if (globalHeader.getLinkType() != GlobalHeader.LINK_TYPE_ETHERNET) {
                System.out.println("Link type is not ethernet!");
                return;
            }

            while (fis.read(packetHeaderBuffer) > 0) {
                // 解析 Packet Header
                PacketHeader packetHeader = parsePacketHeader(packetHeaderBuffer, GlobalHeader.isLittleEndian(globalHeader.getMagic()));
                packetDataBuffer = new byte[packetHeader.getCapLen()];
                if (fis.read(packetDataBuffer) != packetHeader.getCapLen()) {
                    System.out.println("The Pcap file is broken!");
                    return;
                }
                // 解析Packet Data
                parsePacketData(packetDataBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  解析Global Header
     */
    private GlobalHeader parseGlobalHeader(byte[] globalHeaderBuffer) {
        GlobalHeader globalHeader = new GlobalHeader();

        byte[] magicBuffer = Arrays.copyOfRange(globalHeaderBuffer, 0, 4);
        byte[] linkTypeBuffer = Arrays.copyOfRange(globalHeaderBuffer, 20, 24);

        int magic = DataUtils.byteArray2Int(magicBuffer, 4);
        if (GlobalHeader.isLittleEndian(magic)) {
            DataUtils.reverseByteArray(linkTypeBuffer);
        }
        int linkType = DataUtils.byteArray2Int(linkTypeBuffer, 4);
        globalHeader.setMagic(magic);
        globalHeader.setLinkType(linkType);

        return globalHeader;
    }

    /**
     *  解析Packet Header
     */
    private PacketHeader parsePacketHeader(byte[] dataHeaderBuffer, boolean isLittleEndian){

        byte[] timeSBuffer = Arrays.copyOfRange(dataHeaderBuffer, 0, 4);
        byte[] timeMsBuffer = Arrays.copyOfRange(dataHeaderBuffer, 4, 8);
        byte[] capLenBuffer = Arrays.copyOfRange(dataHeaderBuffer, 8, 12);
        byte[] lenBuffer = Arrays.copyOfRange(dataHeaderBuffer, 12, 16);

        PacketHeader packetHeader = new PacketHeader();

        if (isLittleEndian) {
            DataUtils.reverseByteArray(timeSBuffer);
            DataUtils.reverseByteArray(timeMsBuffer);
            DataUtils.reverseByteArray(capLenBuffer);
            DataUtils.reverseByteArray(lenBuffer);
        }

        int timeS = DataUtils.byteArray2Int(timeSBuffer, 4);
        int timeMs = DataUtils.byteArray2Int(timeMsBuffer, 4);
        int capLen = DataUtils.byteArray2Int(capLenBuffer, 4);
        int len = DataUtils.byteArray2Int(lenBuffer, 4);

        packetHeader.setTimeS(timeS);
        packetHeader.setTimeMs(timeMs);
        packetHeader.setCapLen(capLen);
        packetHeader.setLen(len);

        return packetHeader;
    }

    /**
     *  解析PacketData
     */
    private void parsePacketData(byte[] packetDataBuffer) {
        // 仅仅处理以太网的帧
        byte[] framHeaderBuffer = Arrays.copyOfRange(packetDataBuffer, 0, 14);
        FrameHeader frameHeader = frameService.parseFrameHeader(framHeaderBuffer);

        if (frameHeader.getProtocol() != FrameHeader.PROTOCOL_IP) {
            System.out.println("This packet is not IP packet!");
            return;
        }

        // ip包首都的第一个字节的前4位是版本 后四位是首部的长度（单位4字节）
        // expect:0x45--->4:ipv4  5->20字节 69
        // 版本固定是64所以减去64 再 *4就是长度
        int ipHeaderLen = (packetDataBuffer[14] - 64 ) * 4;
        byte[] ipHeaderBuffer = Arrays.copyOfRange(packetDataBuffer, 14, 14 + ipHeaderLen);

        IPHeader ipHeader = ipService.parseIPHeader(ipHeaderBuffer);
        if (ipHeader.getProtocol() != IPHeader.PROTOCOL_TCP) {
            System.out.println("This packet is not TCP segment");
            return;
        }

        // 数据偏移位于TCP字段第13个字节（0开始），占高4位，单位是4字节
        int tcpHeaderLen  = ((packetDataBuffer[14+ipHeaderLen+12] & 0xf0) >> 4) * 4;

        byte[] tcpHeaderBuffer = Arrays.copyOfRange(packetDataBuffer, 14 + ipHeaderLen, 14 + ipHeaderLen + tcpHeaderLen);

        TCPHeader tcpHeader = tcpService.parseTCPHeader(tcpHeaderBuffer);

        System.out.println(ipHeader);
        System.out.println(tcpHeader);
    }

}
