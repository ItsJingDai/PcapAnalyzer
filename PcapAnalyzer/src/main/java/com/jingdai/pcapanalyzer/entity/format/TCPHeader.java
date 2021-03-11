package com.jingdai.pcapanalyzer.entity.format;

/**
 * TCP 包头：20 字节
 */
public class TCPHeader {
	
	/**
	 * 源端口（2 字节）
	 */
	private int srcPort;
	
	/**
	 * 目的端口（2 字节）
	 */
	private int dstPort;
	
	/**
	 * 数据报头的长度(4 bit) + 保留(4 bit) = 1 byte
	 */
	private int headerLen;

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public int getDstPort() {
		return dstPort;
	}

	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}

	public int getHeaderLen() {
		return headerLen;
	}

	public void setHeaderLen(int headerLen) {
		this.headerLen = headerLen;
	}

	public TCPHeader() {}

	@Override
	public String toString() {
		return "TCPHeader [srcPort=" + srcPort
				+ ", dstPort=" + dstPort
				+ ", headerLen=" + headerLen
				+ "]";
	}

}
