package com.jingdai.pcapanalyzer.service;

import com.jingdai.pcapanalyzer.entity.format.FrameHeader;
import com.jingdai.pcapanalyzer.utils.DataUtils;

import java.util.Arrays;

public class FrameService {

    // 目前仅仅解析 以太网类型的帧
    // 没有前导 和 后面的FCS的4个字节
    /*
     *     |  6-bytes dis | 6-bytes sr | 2-bytes protocol type |
     *     |---------------------- 14bytes  -------------------|
     *     IPv4 中type为采用 0x0800
     */
    public FrameHeader parseFrameHeader(byte[] frameHeaderBuffer) {
        FrameHeader frameHeader = new FrameHeader();
        // 目的MAC地址、源MAC地址没用，越过
        byte[] protocolBuffer = Arrays.copyOfRange(frameHeaderBuffer, 12, 14);

        int protocol = DataUtils.byteArray2Int(protocolBuffer, 2);
        frameHeader.setProtocol(protocol);
        return frameHeader;
    }

}
