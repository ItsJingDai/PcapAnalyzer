package com.jingdai.pcapanalyzer.entity.format;

/**
 * TCP header：20 Byte
 */
public class TCPHeader {
	
	/**
	 * source port(2 Byte)
	 */
	private int srcPort;
	
	/**
	 * destination port(2 Byte)
	 */
	private int dstPort;
	
	/**
	 * datagram 4 bit
	 * reserve 4 bit
	 * total 1 Byte
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
