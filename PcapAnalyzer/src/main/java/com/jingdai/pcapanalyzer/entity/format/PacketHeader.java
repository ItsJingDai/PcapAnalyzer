package com.jingdai.pcapanalyzer.entity.format;

/**
 * Pcap Packet Header
 */
public class PacketHeader {
	
	/**
	 * 时间戳 高位（秒）：记录数据包抓获的时间
	 * 记录方式是从格林尼治时间的1970年1月1日 00:00:00 到抓包时经过的秒数（4个字节）
	 */
	private int timeS;	
	/**
	 * 时间戳 低位（微秒）：抓取数据包时的微秒值（4个字节）
	 */
	private int timeMs;						
	/**
	 * 数据包长度：标识所抓获的数据包保存在 pcap 文件中的实际长度，以字节为单位（4个字节）
	 */
	private int capLen;
	/**
	 * 数据包实际长度： 所抓获的数据包的真实长度（4个字节）
	 */
	private int len;

    @Override
    public String toString() {
        return "PacketHeader{" +
                "timeS=" + timeS +
                ", timeMs=" + timeMs +
                ", capLen=" + capLen +
                ", len=" + len +
                '}';
    }

    public int getTimeMs() {
        return timeMs;
    }

    public void setTimeMs(int timeMs) {
        this.timeMs = timeMs;
    }

    public int getCapLen() {
        return capLen;
    }

    public void setCapLen(int capLen) {
        this.capLen = capLen;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getTimeS() {

        return timeS;
    }

    public void setTimeS(int timeS) {
        this.timeS = timeS;
    }

    public PacketHeader() {}
	
}
