package com.jingdai.pcapanalyzer.test;

import com.jingdai.pcapanalyzer.service.PCAPService;
import java.io.File;

public class FirstTest {

    public static void main(String[] args) {
        PCAPService pcapService = new PCAPService();
        File pcapFile = new File("E:\\Wireshark-packages\\test1\\test5.pcap");
        pcapService.parsePcap(pcapFile);
    }
}
